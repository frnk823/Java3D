import java.applet.Applet;
import java.awt.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.mouse.*;

public class Tree extends Applet
{public BranchGroup createBranchGroupSceneGraph()
{BranchGroup BrachGroupRoot =new BranchGroup();
    BoundingSphere bounds=new BoundingSphere(new Point3d(0.0,0.0,0.0),100.0);
    Color3f bgColor=new Color3f(1.0f,1.0f,1.0f);
    Background bg=new Background(bgColor);
    bg.setApplicationBounds(bounds);
    BrachGroupRoot.addChild(bg);
    Color3f directionalColor=new Color3f(1.f,1.f,1.f);
    Vector3f vec=new Vector3f(-1.f,-1.f,-1.0f);
    DirectionalLight directionalLight=new DirectionalLight(directionalColor,vec);
    directionalLight.setInfluencingBounds(bounds);
    BrachGroupRoot.addChild(directionalLight);
    //定义外观和材质
    Appearance app1=new Appearance();
    Material material1=new Material();
    material1.setDiffuseColor(new Color3f(1.0f,.0f,0.0f));
    app1.setMaterial(material1);
    Appearance app2=new Appearance();
    Material material2=new Material();
    material2.setDiffuseColor(new Color3f(.0f,1.0f,0.0f));
    app2.setMaterial(material2);
    Appearance app3=new Appearance();
    Material material3=new Material();
    material3.setDiffuseColor(new Color3f(.0f,.0f,1.0f));
    app3.setMaterial(material3);
    Appearance app4=new Appearance();
    Material material4=new Material();
    material4.setDiffuseColor(new Color3f(1.0f,1.0f,0.0f));
    app4.setMaterial(material4);
    Appearance app5=new Appearance();
    Material material5=new Material();
    material5.setDiffuseColor(new Color3f(.0f,1.0f,1.0f));
    app5.setMaterial(material5);
    Appearance app6=new Appearance();
    Material material6=new Material();
    material6.setDiffuseColor(new Color3f(1.0f,.0f,1.0f));
    app6.setMaterial(material6);

    Transform3D t=new Transform3D();
    t.setTranslation(new Vector3f(0.f,0.3f,0.f));
    TransformGroup transformgroup=new TransformGroup(t);
    transformgroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    transformgroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    BrachGroupRoot.addChild(transformgroup);
    MouseRotate mouserotate = new MouseRotate();
    mouserotate.setTransformGroup(transformgroup);
    BrachGroupRoot.addChild(mouserotate);
    mouserotate.setSchedulingBounds(bounds);
    MouseZoom mousezoom = new MouseZoom();
    mousezoom.setTransformGroup(transformgroup);
    BrachGroupRoot.addChild(mousezoom);
    mousezoom.setSchedulingBounds(bounds);
    MouseTranslate mousetranslate = new MouseTranslate();
    mousetranslate.setTransformGroup(transformgroup);
    BrachGroupRoot.addChild(mousetranslate);
    mousetranslate.setSchedulingBounds(bounds);
//定义基本体及外观属性与坐标变换
    //
    t=new Transform3D();
    t.setTranslation(new Vector3f(0.f,-1.31f,0.f));
    TransformGroup tg1=new TransformGroup(t);
    tg1.addChild(new Cylinder(0.1f,0.8f,app1));
    //
    t=new Transform3D();
    t.setTranslation(new Vector3f(0.f,-0.8f,0.f));
    TransformGroup tg2=new TransformGroup(t);
    tg2.addChild(new Cone(0.8f,0.8f,1,200,200,app2));
    //
    t=new Transform3D();
    t.setTranslation(new Vector3f(0.f,-0.3f,0.f));
    TransformGroup tg3=new TransformGroup(t);
    tg3.addChild(new Cone(0.6f,0.8f,1,200,200,app2));
    //
    t=new Transform3D();
    t.setTranslation(new Vector3f(0.f,0.2f,0.f));
    TransformGroup tg4=new TransformGroup(t);
    tg4.addChild(new Cone(0.4f,0.8f,1,200,200,app2));
    //
    t=new Transform3D();
    t.setTranslation(new Vector3f(0.f,0.7f,0.f));
    TransformGroup tg5=new TransformGroup(t);
    tg5.addChild(new Cone(0.3f,0.8f,1,200,200,app2));

    t=new Transform3D();
    t.setTranslation(new Vector3f(-0.2f,-0.9f,0.48f));
    TransformGroup tg6=new TransformGroup(t);
    tg6.addChild(new Sphere(0.08f,1,100,app3));

    t=new Transform3D();
    t.setTranslation(new Vector3f(0.27f,-0.4f,0.3f));
    TransformGroup tg7=new TransformGroup(t);
    tg7.addChild(new Sphere(0.08f,1,100,app4));

    t=new Transform3D();
    t.setTranslation(new Vector3f(-0.26f,-0.13f,0.28f));
    TransformGroup tg8=new TransformGroup(t);
    tg8.addChild(new Sphere(0.08f,1,100,app5));

    t=new Transform3D();
    t.setTranslation(new Vector3f(0.f,0.39f,0.26f));
    TransformGroup tg9=new TransformGroup(t);
    tg9.addChild(new Sphere(0.08f,1,100,app6));

    t=new Transform3D();
    t.setTranslation(new Vector3f(0.3f,-1f,0.52f));
    TransformGroup tg10=new TransformGroup(t);
    tg10.addChild(new Sphere(0.08f,1,100,app5));

    transformgroup.addChild(tg1);
    transformgroup.addChild(tg2);
    transformgroup.addChild(tg3);
    transformgroup.addChild(tg4);
    transformgroup.addChild(tg5);
    transformgroup.addChild(tg6);
    transformgroup.addChild(tg7);
    transformgroup.addChild(tg8);
    transformgroup.addChild(tg9);
    transformgroup.addChild(tg10);
    BrachGroupRoot.compile();
    return BrachGroupRoot;
}

    public Tree()
    {setLayout(new BorderLayout());
        Panel p = new Panel();
        p.add(new Label("20151681310036 冯懿"));
        add(p, BorderLayout.NORTH);
        GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
        Canvas3D c=new Canvas3D(gc);
        add("Center",c);
        BranchGroup BranchGroupScene=createBranchGroupSceneGraph();
        SimpleUniverse u=new SimpleUniverse(c);
        u.getViewingPlatform().setNominalViewingTransform();
        u.addBranchGraph(BranchGroupScene);
    }
    public static void main(String[] args)
    {new MainFrame(new Tree(),300,650);}}
