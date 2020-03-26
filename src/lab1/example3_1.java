package lab1;

import java.applet.Applet;
import java.awt.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class example3_1 extends Applet{

    public BranchGroup createBranchGroup() {
        BranchGroup BranchGroupRoot = new BranchGroup();                             //BranchGroup
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0),100.0);  //创建球心坐标在0.0.0  半径为100 ?
        Color3f bgColor = new Color3f(0.6f,0.0f,1.0f);               //改变背景颜色
        Background bg = new Background(bgColor);                                     //Background
        bg.setApplicationBounds(bounds);
        BranchGroupRoot.addChild(bg);                                                // BranchGroup addChild (Background)

        Color3f directionalColor = new Color3f(0.0f,1.0f,1.0f);//改变光线颜色
        Vector3f vec = new Vector3f(-1.0f,-1.0f,-1.0f);
        DirectionalLight directionalLight = new DirectionalLight(directionalColor,vec);
        directionalLight.setInfluencingBounds(bounds);
        BranchGroupRoot.addChild(directionalLight);

        Appearance app_1 = new Appearance();                                          //Appearance 外观
        Appearance app_2 = new Appearance();
        Material material_1 = new Material();
        Material material_2 = new Material();
        material_1.setDiffuseColor(new Color3f(1.0f,0.0f,0.0f));        // 改变材质的颜色
        material_2.setDiffuseColor(new Color3f(1.0f,1.0f,1.0f));
        app_1.setMaterial(material_1);
        app_2.setMaterial(material_2);

        // TransformGroup + Mouse rotatr zoom translate

        TransformGroup transformgroup = new TransformGroup();
        transformgroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        transformgroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        BranchGroupRoot.addChild(transformgroup);

        MouseRotate mouserotate = new MouseRotate();
        mouserotate.setTransformGroup(transformgroup);
        BranchGroupRoot.addChild(mouserotate);
        mouserotate.setSchedulingBounds(bounds);

        //////////////////////////////////////////////////////
        MouseZoom mousezoom = new MouseZoom();
        mousezoom.setTransformGroup(transformgroup);
        BranchGroupRoot.addChild(mousezoom);
        mousezoom.setSchedulingBounds(bounds);
        /////////////////////////////////////////////////////这好像没什么用

        MouseTranslate mousetranslate = new MouseTranslate();
        mousetranslate.setTransformGroup(transformgroup);
        BranchGroupRoot.addChild(mousetranslate);
        mousetranslate.setSchedulingBounds(bounds);

        TransformGroup tg_1 = new TransformGroup();
        tg_1.addChild(new Sphere(0.4f,app_1));


        //座标变幻向量
        Transform3D t_1 = new Transform3D();
        t_1.setTranslation(new Vector3f(0.0f,-0.425f,0.0f));
        //Transform3D t_2 = new Transform3D();
        //t_2.setTranslation(new Vector3f(0.0f,+0.425f,0.0f));


        TransformGroup tg_2 = new TransformGroup(t_1);
        tg_2.addChild(new Box(0.5f,0.05f,0.5f,app_2));

        //TransformGroup tg_3 = new TransformGroup(t_2);
        //tg_3.addChild(new Box(0.5f,0.05f,0.5f,app_2));

        transformgroup.addChild(tg_1);
        transformgroup.addChild(tg_2);
        //transformgroup.addChild(tg_3);

        BranchGroupRoot.compile();

        return BranchGroupRoot;
    }

    public example3_1() {
        setLayout(new BorderLayout());
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D c = new Canvas3D(gc);
        add("Center",c);
        BranchGroup BranchGroupScene = createBranchGroup();
        SimpleUniverse u = new SimpleUniverse(c);
        u.getViewingPlatform().setNominalViewingTransform();
        u.addBranchGraph(BranchGroupScene);
    }



    public static void main(String[] args) {
        new MainFrame (new example3_1(),300,300);
    }

}

