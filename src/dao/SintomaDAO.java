package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dto.SintomaDTO;

public class SintomaDAO {
	
	public List<SintomaDTO> buscarSintomasPorInicial (String inicial)
	{
		List<SintomaDTO> lista_sintomas = new ArrayList<SintomaDTO>();
		
			String des_sintoma = null;
			int id_sintoma = 0;
			
			Pool pool = null;
			pool = Pool.getInstance();
			Connection conn = pool.getConnection();
			ResultSet rset = null;
			Statement stmt = null;
			SintomaDTO sintomaDTO = null;
			try
			{
				stmt = conn.createStatement();
				rset = stmt.executeQuery(Consultas.CONSULTA_SINTOMAS_POR_INICIAL+inicial+"%'");
				while (rset.next())
			    {
					id_sintoma = rset.getInt(1);
					des_sintoma = rset.getString(2);
					sintomaDTO = new SintomaDTO(id_sintoma, des_sintoma);
					lista_sintomas.add(sintomaDTO);
			    }
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			finally 
			{
				pool.liberarRecursos(conn, stmt, rset);
			}
		
		return lista_sintomas;
	}

}
