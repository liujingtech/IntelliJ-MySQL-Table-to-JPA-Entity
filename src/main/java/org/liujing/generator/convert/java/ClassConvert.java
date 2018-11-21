package org.liujing.generator.convert.java;

import org.liujing.generator.Util;
import org.liujing.generator.object.java.AnnotationObject;
import org.liujing.generator.object.java.ClassObject;
import org.liujing.generator.object.java.MemberObject;
import org.liujing.generator.object.sql.TableObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ClassConvert {

    public static ClassObject convertToClassObject(TableObject tableObject) {
        ClassObject mClassObject = new ClassObject();
        //set comment
        mClassObject.setComment(tableObject.getComment());
        //set default class annotation
        ArrayList<AnnotationObject> defaultAnnotationList = new ArrayList<>();
        getDefualtClassAnnotation(tableObject, defaultAnnotationList);
        mClassObject.setAnnotationList(defaultAnnotationList);
        //set class name
        mClassObject.setName(Util.toUpperCamelCase(tableObject.getName()));
        //set class member list
        ArrayList<MemberObject> memberObjectList = new ArrayList<>();
        tableObject.getColumnObjectList().forEach(columnObject -> {
            MemberObject memberObject = MemberConvert.convertMemberObject(columnObject);
            memberObjectList.add(memberObject);
        });
        mClassObject.setMemberObjectList(memberObjectList);

        return mClassObject;
    }

    /**
     * Get default Member Annotation
     */
    private static void getDefualtClassAnnotation(TableObject tableObject, ArrayList<AnnotationObject> defaultAnnotationList) {
        AnnotationObject entity = new AnnotationObject();
        entity.setFullName("javax.persistence.Entity");
        entity.setName("Entity");
        defaultAnnotationList.add(entity);

        AnnotationObject table = new AnnotationObject();
        table.setFullName("javax.persistence.Table");
        table.setName("Table");
        Map<String, Object> tableMap = new HashMap<>();
        tableMap.put("name", tableObject.getName());
        table.setValue(tableMap);
        defaultAnnotationList.add(table);
// spring data core
//        AnnotationObject entityListeners = new AnnotationObject();
//        entityListeners.setFullName("javax.persistence.EntityListeners");
//        entityListeners.setName("EntityListeners");
//        Map<String, Object> entityListenersMap = new HashMap<>();
//        entityListenersMap.put("", AuditingEntityListener.class);
//        entityListeners.setValue(entityListenersMap);
//        defaultAnnotationList.add(entityListeners);

    }

}
