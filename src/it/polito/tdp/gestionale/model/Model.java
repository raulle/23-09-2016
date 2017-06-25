package it.polito.tdp.gestionale.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.gestionale.db.DidatticaDAO;

public class Model {
	
	private DidatticaDAO dao;
	private UndirectedGraph<Nodo,DefaultEdge> grafo;
	
	public Model(){
		this.dao= new DidatticaDAO();
	}
	
	public void creaGrafo(){
		this.grafo= new SimpleGraph<Nodo,DefaultEdge>(DefaultEdge.class);
		List<Nodo> nod = dao.getStudenti();
		nod.addAll(dao.getCorsi());
		Graphs.addAllVertices(grafo, nod);
			
		for(Nodo n : grafo.vertexSet()){
			if(n instanceof Studente){
				for(Nodo nn : grafo.vertexSet()){
					if(nn instanceof Corso){
						if(dao.isIscritto(((Studente) n).getMatricola(), ((Corso) nn).getCodins())){
							DefaultEdge e = grafo.addEdge(n, nn);
						}
					}
				}
			}
		}
			
		
		//System.out.println(grafo);
	}
	
	private int getStudMaxCorsi(){
		int max=0;
		for(Nodo n : grafo.vertexSet()){
			if(n instanceof Studente){
				int g= grafo.degreeOf(n);
				if(g>max){
					max=g;
				}
			}
		}
		return max;
	}
	
	private List<Nodo> getStudenti(int i){
		List<Nodo> s= new ArrayList<>();
		for(Nodo n : grafo.vertexSet()){
			if(n instanceof Studente){
				if(grafo.degreeOf(n)==i){
					s.add(n);
				}
			}
		}
		return s;
	}
	
	public String gg(){
		if(grafo==null)
			this.creaGrafo();
		String s="";
		for(int i=0;i<=this.getStudMaxCorsi();i++){
			s+="Studenti con numero di corsi = "+i+" :\n"+this.getStudenti(i).toString()+"\n";
		}
		return s;
	}

}
