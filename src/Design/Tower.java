package Design;

import java.applet.Applet;
import java.awt.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.mouse.*;

public class Tower extends Applet
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
    //地板
    t=new Transform3D();
    t.setTranslation(new Vector3f(0.f,-1.4f,0.f));
    TransformGroup tg1=new TransformGroup(t);
    tg1.addChild(new Box(0.65f,0.025f,0.65f,app2));
    //大球
    t=new Transform3D();
    t.setTranslation(new Vector3f(0.f,-0.7f,0.f));
    TransformGroup tg2=new TransformGroup(t);
    tg2.addChild(new Sphere(0.3f,1,100,app1));
    //中球
    t=new Transform3D();
    t.setTranslation(new Vector3f(0.f,0.3f,0.f));
    TransformGroup tg3=new TransformGroup(t);
    tg3.addChild(new Sphere(0.25f,1,100,app3));
    //小球
    t=new Transform3D();
    t.setTranslation(new Vector3f(0.f,1.0f,0.f));
    TransformGroup tg4=new TransformGroup(t);
    tg4.addChild(new Sphere(0.1f,1,100,app4));
    //尖头
    t=new Transform3D();
    t.setTranslation(new Vector3f(0.f,.9f,0.f));
    TransformGroup tg5=new TransformGroup(t);
    tg5.addChild(new Cone(0.05f,0.9f,1,200,200,app1));
    //砥柱1-4
    t=new Transform3D();
    t.rotZ(-Math.PI/6);
    t.setTranslation(new Vector3f(-0.1f,-1.f,0.f));
    TransformGroup tg6=new TransformGroup(t);
    tg6.addChild(new Cylinder(0.05f,0.92f,app3));

    t=new Transform3D();
    t.rotZ(Math.PI/6);
    t.setTranslation(new Vector3f(0.1f,-1.f,0.f));
    TransformGroup tg7=new TransformGroup(t);
    tg7.addChild(new Cylinder(0.05f,.92f,app3));

    t=new Transform3D();
    t.rotX(Math.PI/6);
    t.setTranslation(new Vector3f(0.f,-1.f,-0.1f));
    TransformGroup tg8=new TransformGroup(t);
    tg8.addChild(new Cylinder(0.05f,.92f,app3));

    t=new Transform3D();
    t.rotX(-Math.PI/6);
    t.setTranslation(new Vector3f(0.f,-1.f,0.1f));
    TransformGroup tg9=new TransformGroup(t);
    tg9.addChild(new Cylinder(0.05f,.92f,app3));

    t=new Transform3D();
    t.setTranslation(new Vector3f(0.f,-0.2f,-0.1f));
    TransformGroup tg10=new TransformGroup(t);
    tg10.addChild(new Cylinder(0.04f,.92f,app2));

    t=new Transform3D();
    t.setTranslation(new Vector3f(-0.1f,-0.2f,0.1f));
    TransformGroup tg11=new TransformGroup(t);
    tg11.addChild(new Cylinder(0.04f,.92f,app2));

    t=new Transform3D();
    t.setTranslation(new Vector3f(0.1f,-0.2f,0.1f));
    TransformGroup tg12=new TransformGroup(t);
    tg12.addChild(new Cylinder(0.04f,.92f,app2));

    t=new Transform3D();
    t.rotZ(Math.PI/2);
    t.setTranslation(new Vector3f(0.f,-0.2f,0.1f));
    TransformGroup tg13=new TransformGroup(t);
    tg13.addChild(new Cylinder(0.03f,.2f,app2));

    t=new Transform3D();
    t.rotZ(Math.PI/2);
    t.setTranslation(new Vector3f(0.f,-0.05f,0.1f));
    TransformGroup tg14=new TransformGroup(t);
    tg14.addChild(new Cylinder(0.03f,.2f,app2));

    t=new Transform3D();
    t.rotZ(Math.PI/2);
    t.setTranslation(new Vector3f(0.f,-0.35f,0.1f));
    TransformGroup tg15=new TransformGroup(t);
    tg15.addChild(new Cylinder(0.03f,.2f,app2));

    t=new Transform3D();
    t.rotX(Math.PI/2);
    t.setTranslation(new Vector3f(-0.05f,-0.2f,0.0f));
    TransformGroup tg16=new TransformGroup(t);
    tg16.addChild(new Cylinder(0.02f,.2f,app2));

    t=new Transform3D();
    t.rotX(Math.PI/2);
    t.setTranslation(new Vector3f(-0.05f,-0.05f,0.0f));
    TransformGroup tg17=new TransformGroup(t);
    tg17.addChild(new Cylinder(0.02f,.2f,app2));

    t=new Transform3D();
    t.rotX(Math.PI/2);
    t.setTranslation(new Vector3f(-0.05f,-0.35f,0.0f));
    TransformGroup tg18=new TransformGroup(t);
    tg18.addChild(new Cylinder(0.02f,.2f,app2));

    t=new Transform3D();
    t.rotX(Math.PI/2);
    t.setTranslation(new Vector3f(0.05f,-0.2f,0.0f));
    TransformGroup tg19=new TransformGroup(t);
    tg19.addChild(new Cylinder(0.02f,.2f,app2));

    t=new Transform3D();
    t.rotX(Math.PI/2);
    t.setTranslation(new Vector3f(0.05f,-0.05f,0.0f));
    TransformGroup tg20=new TransformGroup(t);
    tg20.addChild(new Cylinder(0.02f,.2f,app2));

    t=new Transform3D();
    t.rotX(Math.PI/2);
    t.setTranslation(new Vector3f(0.05f,-0.35f,0.0f));
    TransformGroup tg21=new TransformGroup(t);
    tg21.addChild(new Cylinder(0.02f,.2f,app2));

    t=new Transform3D();
    t.setTranslation(new Vector3f(-0.22f,-1.2f,0.f));
    TransformGroup tg22=new TransformGroup(t);
    tg22.addChild(new Sphere(0.08f,1,100,app4));

    t=new Transform3D();
    t.setTranslation(new Vector3f(0.22f,-1.2f,0.f));
    TransformGroup tg23=new TransformGroup(t);
    tg23.addChild(new Sphere(0.08f,1,100,app4));

    t=new Transform3D();
    t.setTranslation(new Vector3f(-0.f,-1.2f,-0.22f));
    TransformGroup tg24=new TransformGroup(t);
    tg24.addChild(new Sphere(0.08f,1,100,app4));

    t=new Transform3D();
    t.setTranslation(new Vector3f(-0.f,-1.2f,0.22f));
    TransformGroup tg25=new TransformGroup(t);
    tg25.addChild(new Sphere(0.08f,1,100,app4));


    /*
    t=new Transform3D();
//让圆柱体绕向量（1,1,1）逆时针旋转45度
    Vector3f vector3f=new Vector3f(1.f,1.f,1.f);
    vector3f.normalize();
    float theta=(float)Math.PI/4f;
    Quat4f quat=new Quat4f();
    quat.x=(float)Math.sin(theta/2f)*vector3f.x;
    quat.y=(float)Math.sin(theta/2f)*vector3f.y;
    quat.x=(float)Math.sin(theta/2f)*vector3f.z;
    quat.w=(float)Math.cos(theta/2f);
    t.setRotation(quat);
    t.setTranslation(new Vector3f(-0.5f,-.1f,0.f));
    TransformGroup tg9=new TransformGroup(t);
    tg9.addChild(new Cylinder(0.05f,0.8f,app1));*/


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
    transformgroup.addChild(tg11);
    transformgroup.addChild(tg12);
    transformgroup.addChild(tg13);
    transformgroup.addChild(tg14);
    transformgroup.addChild(tg15);
    transformgroup.addChild(tg16);
    transformgroup.addChild(tg17);
    transformgroup.addChild(tg18);
    transformgroup.addChild(tg19);
    transformgroup.addChild(tg20);
    transformgroup.addChild(tg21);
    transformgroup.addChild(tg22);
    transformgroup.addChild(tg23);
    transformgroup.addChild(tg24);
    transformgroup.addChild(tg25);

    BrachGroupRoot.compile();
    return BrachGroupRoot;
}

    public Tower()
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
    {new MainFrame(new Tower(),300,600);}}
