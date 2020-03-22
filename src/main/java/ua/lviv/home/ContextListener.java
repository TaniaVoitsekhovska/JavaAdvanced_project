package ua.lviv.home;

//
//@WebListener("application context listener to init log4j")
//public class ContextListener implements ServletContextListener {
//
//    /**
//     * Initialize log4j when the application is being started
//     */
//    @Override
//    public void contextInitialized(ServletContextEvent event) {
//        // initialize log4j here
//        ServletContext context = event.getServletContext();
//        String log4jConfigFile = context.getInitParameter("log4j-config");
//        String fullPath = context.getRealPath("") + File.separator + log4jConfigFile;
//
//        DOMConfigurator.configure(fullPath);
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent event) {
//        // do nothing
//    }
//}
