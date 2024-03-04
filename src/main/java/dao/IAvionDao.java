package dao;
import java.util.List;
import aeroport.entities.Avion;
public interface IAvionDao {
public Avion save(Avion p);
public List<Avion> avionsParMC(String mc);
public Avion getAvion(Long id);
public Avion updateAvion(Avion p);
public void deleteAvion(Long id);
}