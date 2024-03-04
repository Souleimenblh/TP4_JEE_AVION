package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import aeroport.SingletonConnection;
import aeroport.entities.Avion;

public class AvionDaoImpl implements IAvionDao {
@Override
public Avion save(Avion p) {
Connection conn=SingletonConnection.getConnection();
 try {
PreparedStatement ps= conn.prepareStatement("INSERT INTO AVIONS(NOM_AVION,PRIX) VALUES(?,?)");
ps.setString(1, p.getNomAvion());
ps.setDouble(2, p.getPrix());
ps.executeUpdate();
PreparedStatement ps2= conn.prepareStatement
("SELECT MAX(ID_AVION) as MAX_ID FROM AVIONS");
ResultSet rs =ps2.executeQuery();
if (rs.next()) {
p.setIdAvion(rs.getLong("MAX_ID"));
}
ps.close();
ps2.close();
} catch (SQLException e) {
e.printStackTrace();
}
return p;
}
@Override
public List<Avion> avionsParMC(String mc) {
 List<Avion> avios= new ArrayList<Avion>();
 Connection conn=SingletonConnection.getConnection();
 try {
PreparedStatement ps= conn.prepareStatement("select * from AVIONS where NOM_AVION LIKE ?");
ps.setString(1, "%"+mc+"%");
ResultSet rs = ps.executeQuery();
while (rs.next()) {
Avion p = new Avion();
p.setIdAvion(rs.getLong("ID_Avion"));
p.setNomAvion(rs.getString("NOM_Avion"));
p.setPrix(rs.getDouble("PRIX"));
avios.add(p);
}
} catch (SQLException e) {
e.printStackTrace();
}
return avios;
}
@Override
public Avion getAvion(Long id) {
// TODO Auto-generated method stub
return null;
}
@Override
public Avion updateAvion(Avion p) {
// TODO Auto-generated method stub
return null;
}
@Override
public void deleteAvion(Long id) {
// TODO Auto-generated method stub
}
}
