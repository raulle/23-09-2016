package it.polito.tdp.gestionale.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.gestionale.model.Corso;
import it.polito.tdp.gestionale.model.Nodo;
import it.polito.tdp.gestionale.model.Studente;

public class DidatticaDAO {

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String codins) {

		final String sql = "SELECT * FROM corso where codins=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, codins);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Corso corso = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"),
						rs.getInt("pd"));
				return corso;
			}

			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Data una matricola ottengo lo studente.
	 */
	public Studente getStudente(int matricola) {

		final String sql = "SELECT * FROM studente where matricola=?";

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);

			ResultSet rs = st.executeQuery();

			if (rs.next()) {

				Studente studente = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"),
						rs.getString("cds"));
				return studente;
			}

			return null;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
		
		public List<Nodo> getStudenti() {

			final String sql = "SELECT * FROM studente order by matricola";

			try {
				Connection conn = DBConnect.getConnection();
				PreparedStatement st = conn.prepareStatement(sql);

				ResultSet rs = st.executeQuery();

				List<Nodo> l = new ArrayList<>();
				while (rs.next()) {

					Studente studente = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"),
							rs.getString("cds"));
					l.add(studente);
				}

				return l;

			} catch (SQLException e) {
				// e.printStackTrace();
				throw new RuntimeException("Errore Db");
			}
		}
			
			public List<Nodo> getCorsi() {

				final String sql = "SELECT * FROM corso order by codins";

				try {
					Connection conn = DBConnect.getConnection();
					PreparedStatement st = conn.prepareStatement(sql);

					ResultSet rs = st.executeQuery();

					List<Nodo> l = new ArrayList<>();
					while (rs.next()) {

						Corso corso = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"),
								rs.getInt("pd"));
						l.add(corso);
					}

					return l;

				} catch (SQLException e) {
					// e.printStackTrace();
					throw new RuntimeException("Errore Db");
				}
			}
				
				public boolean isIscritto(int matricola, String codIns) {

					final String sql = "select * from iscrizione i where i.matricola=? and i.codins=?";

					try {
						Connection conn = DBConnect.getConnection();
						PreparedStatement st = conn.prepareStatement(sql);
						st.setInt(1, matricola);
						st.setString(2, codIns);

						ResultSet rs = st.executeQuery();
						
						boolean x=false;
						if (rs.next()) {
							x=true;
						}

						return x;

					} catch (SQLException e) {
						// e.printStackTrace();
						throw new RuntimeException("Errore Db");
					}
			}
		
	

	// Test main
	public static void main(String[] args) {
		
		DidatticaDAO dao = new DidatticaDAO();
		//System.out.println(dao.getStudenti());
		System.out.println(dao.isIscritto(82751, "01NBAPG"));
	}

}
