package web;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.catalina.connector.Response;

import dao.IAvionDao;
import dao.AvionDaoImpl;
import aeroport.entities.Avion;


@WebServlet (name="cs",urlPatterns= {"/controleur","*.do"})
public class ControleurServlet extends HttpServlet {
IAvionDao aeroport;
@Override
public void init() throws ServletException {
aeroport = new AvionDaoImpl();
}
@Override
protected void doGet(HttpServletRequest request,
 HttpServletResponse response) 
 throws ServletException, IOException {
String path=request.getServletPath();
if (path.equals("/index.do"))
{
request.getRequestDispatcher("avions.jsp").forward(request,response);
}
else if (path.equals("/chercher.do"))
{
String motCle=request.getParameter("motCle");
AvionModele model= new AvionModele();
model.setMotCle(motCle);
List<Avion> avios = aeroport.avionsParMC(motCle);
model.setAvions(avios);
request.setAttribute("model", model);
request.getRequestDispatcher("avions.jsp").forward(request,response);
}

else if (path.equals("/saisie.do") )
{
request.getRequestDispatcher("saisieAvion.jsp").forward(request,response);
}
else if (path.equals("/save.do") && request.getMethod().equals("POST"))
{
 String nom=request.getParameter("nom");
double prix = Double.parseDouble(request.getParameter("prix"));
Avion a = aeroport.save(new Avion(nom,prix));
request.setAttribute("avion", a);
request.getRequestDispatcher("confirmation.jsp").forward(request,response);
}
else if (path.equals("/supprimer.do"))
{
 Long id= Long.parseLong(request.getParameter("id"));
 aeroport.deleteAvion(id);
 response.sendRedirect("chercher.do?motCle=");
}
else if (path.equals("/editer.do") )
{
Long id= Long.parseLong(request.getParameter("id"));
Avion a = aeroport.getAvion(id);
 request.setAttribute("avion", a);
request.getRequestDispatcher("editerAvion.jsp").forward(request,response);
}
else if (path.equals("/update.do") )
{
Long id = Long.parseLong(request.getParameter("id"));
String nom=request.getParameter("nom");
double prix = 
Double.parseDouble(request.getParameter("prix"));
Avion a = new Avion();
a.setIdAvion(id);
a.setNomAvion(nom);
a.setPrix(prix);
aeroport.updateAvion(a);
request.setAttribute("avion", a);
request.getRequestDispatcher("confirmation.jsp").forward(request,response);
}
else
{
response.sendError(Response.SC_NOT_FOUND);
}
}

@Override
protected void doPost(HttpServletRequest request, 
 HttpServletResponse response) throws 
ServletException, IOException {
doGet(request,response);
}
}
