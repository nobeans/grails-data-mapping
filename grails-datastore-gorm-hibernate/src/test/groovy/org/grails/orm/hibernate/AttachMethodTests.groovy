package org.grails.orm.hibernate

import grails.persistence.Entity
import org.junit.Test
import static junit.framework.Assert.*
/**
 * @author Graeme Rocher
 * @since 1.0
 *
 * Created: Jul 21, 2008
 */
class AttachMethodTests extends AbstractGrailsHibernateTests {

    protected getDomainClasses() {
        [AttachMethod]
    }

    @Test
    void testAttachMethod() {
        def testClass = ga.getDomainClass(AttachMethod.name).clazz

        def test = testClass.newInstance(name:"foo")

        assertNotNull test.save(flush:true)

        assertTrue session.contains(test)
        assertTrue test.attached
        assertTrue test.isAttached()

        test.discard()

        assertFalse session.contains(test)
        assertFalse test.isAttached()
        assertFalse test.attached

        test.attach()

        assertTrue session.contains(test)
        assertTrue test.isAttached()
        assertTrue test.attached

        test.discard()

        assertEquals test, test.attach()
    }
}

@Entity
class AttachMethod {
    Long id
    Long version
    String name
}
