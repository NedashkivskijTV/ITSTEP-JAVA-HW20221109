package edu.itstep.phonebook.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * залишається без змін
     * не перевизначається – використовується якщо є ще один головний корньовий конфігураційний файл
     *
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    /**
     * вказується клас, що виконує роль конфігураційного файлу applicationContext.xml
     * це варіант реалізації конфігураційного файла configuration file
     *
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MyConfig.class};
    }

    /**
     * вказує на яку URL-адресу реагуватиме DispatcherServlet (Front Controller)
     * / - при використанні даного символа DispatcherServlet реагуватиме на усі запити
     *
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] {"/"};
    }
}
