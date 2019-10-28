
/*********************************
Groovy script that allows executing SQLs

It is very useful if we have no access to a database
**********************************/


/*********** EXAMPLE: ***********/

LiferayUtil liferayUtil = new LiferayUtil(out);
/* select a ddmstructure: */
liferayUtil.executeSQL("SELECT * FROM lportal.gamification_BadgeType;","      ");

/*********** SCRIPT CODE ***********/

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.ResultSetMetaData;
import com.liferay.portal.kernel.dao.jdbc.DataAccess;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.HtmlUtil;

public class LiferayUtil {

	PrintWriter out = null;
	
	LiferayUtil(PrintWriter out) {
		this.out = out;
	}

	public void executeSQL(String sql, String sep) throws IOException{
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = DataAccess.getConnection();

			sql = PortalUtil.transformSQL(sql);

			out.println("");
			out.println("SQL: "+sql);
			out.println("");

			ps = con.prepareStatement(sql);

			rs = ps.executeQuery();

			ResultSetMetaData rsmd = rs.getMetaData();

			int numberOfColumns = rsmd.getColumnCount();

			int rows = 0;

			for (int i = 1; i <= numberOfColumns; i++) {
				if (i > 1) out.print(sep);
				String columnName = rsmd.getColumnName(i);
				out.print(columnName);
			}
			out.println("");

			while (rs.next()) {
				for (int i = 1; i <= numberOfColumns; i++) {
					if (i > 1) out.print(sep);
					String columnValue = rs.getString(i);
					out.print(HtmlUtil.escape(columnValue));
				}
				out.println("");
				rows++;
			}
			out.println("("+rows+" rows returned)");
			out.println("");
		}
		catch(Exception e) {
			e.printStackTrace(out);
		}
		finally {
			DataAccess.cleanUp(con, ps, rs);
		}
	}
}

/*********** END OF SCRIPT ***********/