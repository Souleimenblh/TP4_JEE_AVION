package web;

import java.util.ArrayList;
import java.util.List;
import aeroport.entities.Avion;


public class AvionModele {
private String motCle;
List<Avion> avions = new ArrayList<>();
public String getMotCle() {
return motCle;
}
public void setMotCle(String motCle) {
this.motCle = motCle;
}
public List<Avion> getAvions() {
return avions;
}
public void setAvions(List<Avion> avions) {
this.avions = avions;
}
}
