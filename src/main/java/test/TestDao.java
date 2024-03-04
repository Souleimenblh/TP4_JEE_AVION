package test;

import java.util.List;

import aeroport.entities.Avion;
import dao.AvionDaoImpl;


public class TestDao {

	public static void main(String[] args) {
		AvionDaoImpl avio= new AvionDaoImpl();
		Avion avi= avio.save(new Avion("Boeing 777",15000));
		System.out.println(avi);
		List<Avion> avios =avio.avionsParMC("Boeing");
		for (Avion a : avios)
		System.out.println(a);
		}
	}