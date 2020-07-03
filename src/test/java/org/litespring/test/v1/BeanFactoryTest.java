package org.litespring.test.v1;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.litespring.beans.BeanDefinition;
import org.litespring.beans.factory.BeanCreationException;
import org.litespring.beans.factory.BeanDefinitionStoreException;
import org.litespring.beans.factory.support.DefaultBeanFactory;
import org.litespring.beans.factory.xml.XmlBeanDefinitionReader;
import org.litespring.core.io.ClassPathResource;
import org.litespring.core.io.Resource;
import org.litespring.service.v1.PetStoreService;

public class BeanFactoryTest {

    DefaultBeanFactory factory = null;
    XmlBeanDefinitionReader reader = null;
    Resource resource = null;

    @Before
    public void setUp(){
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
    }


    @Test
    public void testGetBean() {
        resource = new ClassPathResource("petstore-v1.xml");
        reader.loadBeanDefinitions(resource);
        BeanDefinition bd = factory.getBeanDefinition("petStore");

        Assert.assertTrue(bd.isSingleton());

        Assert.assertFalse(bd.isPrototype());

        Assert.assertEquals(BeanDefinition.SCOPE_DEFAULT,bd.getScope());

        Assert.assertEquals("org.litespring.service.v1.PetStoreService",
                bd.getBeanClassName());

        PetStoreService petStoreService = (PetStoreService) factory.getBean("petStore");
        Assert.assertNotNull(petStoreService);

        PetStoreService petStoreService1 = (PetStoreService) factory.getBean("petStore");
        Assert.assertTrue(petStoreService.equals(petStoreService1));

    }

    @Test
    public void testGetPrototypeBean(){
        resource = new ClassPathResource("petstore-v1.xml");
        reader.loadBeanDefinitions(resource);
        BeanDefinition bd = factory.getBeanDefinition("petStore");

        Assert.assertTrue(bd.isSingleton());

        Assert.assertFalse(bd.isPrototype());

        Assert.assertEquals(BeanDefinition.SCOPE_DEFAULT,bd.getScope());

        Assert.assertEquals("org.litespring.service.v1.PetStoreService",
                bd.getBeanClassName());

        PetStoreService petStoreService = (PetStoreService) factory.getBean("petStore");
        Assert.assertNotNull(petStoreService);

        PetStoreService petStoreService1 = (PetStoreService) factory.getBean("petStore");
        Assert.assertNotNull(petStoreService1);

        Assert.assertTrue(petStoreService.equals(petStoreService1));
    }

    @Test
    public void testInvalidBean() {
        resource = new ClassPathResource("petstore-v1.xml");
        reader.loadBeanDefinitions(resource);


        try {
            factory.getBean("invalidBean");
        } catch (BeanCreationException bcex) {
            bcex.printStackTrace();
            return;
        }

        Assert.fail("exception: BeanCreationException");
    }

    @Test
    public void testInvalidXML() {
        try {
            resource = new ClassPathResource("xxx.xml");
            reader.loadBeanDefinitions(resource);

        } catch (BeanDefinitionStoreException e) {
            e.printStackTrace();
            return;
        }
        Assert.fail("exception: BeanDefinitionStoreException");
    }

}
