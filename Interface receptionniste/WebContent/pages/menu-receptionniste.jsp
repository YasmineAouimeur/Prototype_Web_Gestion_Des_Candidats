<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

   <aside class="main-sidebar">
    <!-- sidebar: style can be found in sidebar.less -->
    <section class="skin-yellow sidebar">
      <!-- Sidebar user panel -->

      <!-- search form -->

      <!-- /.search form -->
      
      
      <!-- sidebar menu: : style can be found in sidebar.less -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">      MENU</li>
        
        
        
        <!-- Menu Elements -->
        
        <li> <!-- Liste des candidats -->
          <a href="http://localhost:8090/interfaceProject/pages/receptionniste/liste-candidats.jsp">
            <i class="fa fa-users"></i> <span>Liste des candidats</span>
            <span class="pull-right-container">
              <span class="label label-primary pull-right"></span>
            </span>
          </a>
          
        </li>
        
        <li> <!-- Dossies en attente -->
          <a href="http://localhost:8090/interfaceProject/pages/receptionniste/DossiersAttentes.jsp">
            <i class="fa fa-folder"></i> <span>Dossiers en attentes</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-green"></small>
            </span>
          </a>
          
        </li>
        
        
        <li> <!-- Creer compte candidat -->
          <a href="http://localhost:8090/interfaceProject/pages/receptionniste/creerCandidat.jsp">
            <i class="fa fa-user-plus"></i> <span>Creer compte candidat</span>
            <span class="pull-right-container">
              <small class="label pull-right bg-green"></small>
            </span>
          </a>
          
        </li>
                    
        <!-- Menu Elements -->
    
    </section>
    <!-- /.sidebar -->
  </aside>
       

</body>
</html>