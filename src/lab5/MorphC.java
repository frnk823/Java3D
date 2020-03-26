package lab5;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.behaviors.mouse.*;
import javax.media.j3d.*;
import javax.vecmath.*;
public class MorphC extends Applet
{public BranchGroup createBranchGroupSceneGraph()
{BranchGroup BranchGroupRoot =new BranchGroup();
    BoundingSphere bounds=new BoundingSphere(new Point3d(0.0,0.0,0.0),100.0);
    Color3f bgColor=new Color3f(1.0f,1.0f,1.0f);
    Background bg=new Background(bgColor);
    bg.setApplicationBounds(bounds);
    BranchGroupRoot.addChild(bg);
    Color3f directionalColor=new Color3f(1.f,0.f,0.f);
    Vector3f vec=new Vector3f(0.f,0.f,-1.0f);
    DirectionalLight directionalLight=new DirectionalLight(directionalColor,vec);
    directionalLight.setInfluencingBounds(bounds);
    BranchGroupRoot.addChild(directionalLight);
    Transform3D t = new Transform3D();
    t.setScale(0.9);
    TransformGroup transformgroup=new TransformGroup(t);
    transformgroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    transformgroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    BranchGroupRoot.addChild(transformgroup);
    MouseRotate mouserotate = new MouseRotate();
    mouserotate.setTransformGroup(transformgroup);
    BranchGroupRoot.addChild(mouserotate);
    mouserotate.setSchedulingBounds(bounds);
//指定外观，这样才有明暗效果
    PolygonAttributes polygona1=new PolygonAttributes();
    polygona1.setBackFaceNormalFlip(true);
    polygona1.setCullFace(PolygonAttributes.CULL_NONE);
    Appearance appearance1 = new Appearance();
    appearance1.setPolygonAttributes(polygona1);
    Material material1 = new Material();
    Color3f color1 = new Color3f(1.0f, .0f, .0f);
    material1.setDiffuseColor(color1);
    appearance1.setMaterial(material1);
    TransparencyAttributes transparency=new TransparencyAttributes(1,0.5f);
    appearance1.setTransparencyAttributes(transparency);
//定义GeometryArray数组
    GeometryArray[] geoms=new GeometryArray[4];
//定义产生旋转体的给定的5个点的x、y坐标，绕y轴旋转
    float[] Xp0={.1f,.1f,.5f,.1f,.1f};
    float[] Yp0={-.4f,-.2f,0.f,.2f,.5f};
    geoms[0] = createGeometry(Xp0,Yp0);
    float[] Xp1={.1f,.1f,.7f,.2f,0.9f};
    float[] Yp1={-.4f,-.2f,0.f,.2f,.5f};
    geoms[1] = createGeometry(Xp1,Yp1);
    float[] Xp2={.9f,.6f,.05f,.6f,.9f};
    float[] Yp2={-.4f,-.2f,0.f,.2f,.5f};
    geoms[2] = createGeometry(Xp2,Yp2);
    float[] Xp3={.01f,.1f,.05f,.1f,.01f};
    float[] Yp3={-.4f,-.2f,0.f,.2f,.5f};
    geoms[3] = createGeometry(Xp3,Yp3);
//定义Morph类的对象
    Morph morph=new Morph(geoms,appearance1);
    morph.setCapability(Morph.ALLOW_WEIGHTS_READ);
    morph.setCapability(Morph.ALLOW_WEIGHTS_WRITE);
    transformgroup.addChild(morph);
//定义Alpha类
    Alpha morphalpha=new Alpha(-1, Alpha.INCREASING_ENABLE|
            Alpha.DECREASING_ENABLE,0,0,10000,10000,5000,10000,10000,5000);
//定义MorphingBehavior类的功能
    MorphingBehavior mbeh=new MorphingBehavior(morphalpha,morph);
    mbeh.setSchedulingBounds(bounds);
    BranchGroupRoot.addChild(mbeh);
    BranchGroupRoot.compile();
    return BranchGroupRoot;
}
    public  MorphC()
    {setLayout(new BorderLayout());
        Panel p = new Panel();
        p.add(new Label("20151681310036 冯懿"));
        add(p, BorderLayout.NORTH);
        GraphicsConfiguration gc=SimpleUniverse.getPreferredConfiguration();
        Canvas3D c=new Canvas3D(gc);
        add("Center",c);
        BranchGroup BranchGroupScene=createBranchGroupSceneGraph();
        SimpleUniverse u=new SimpleUniverse(c);
        u.getViewingPlatform().setNominalViewingTransform();
        u.addBranchGraph(BranchGroupScene);
    }
    public static void main(String argv[])
    {new MainFrame(new MorphC(), 730, 730);}
    //定义生成旋转体的方法，给定五个点的x、y坐标，绕y轴旋转生成旋转体
    GeometryArray createGeometry(float[] Xp0,float[] Yp0 )
    { int i,j,k,c;   int n0=50;
        float theta;
//定义在xoy平面内的旋转线，旋转轴为y轴
//float[] Xp0={.1f,.1f,.5f,.1f,.1f};
//float[] Yp0={-.4f,-.2f,0.f,.2f,.5f};
//计算对圆周50等分后所得的旋转角
        theta=2.f*(float)Math.PI/n0;
        float[][][] SurfacePointsxyz=new float[5][51][3];
//计算旋转后旋转平面上点的x、y、z坐标值
        for(i=0;i<5;i++)
            for(j=0;j<n0+1;j++)
            {SurfacePointsxyz[i][j][0]=Xp0[i]*(float)Math.cos(theta*j);
                SurfacePointsxyz[i][j][1]=Yp0[i];
                SurfacePointsxyz[i][j][2]=Xp0[i]*(float)Math.sin(theta*j); }
//按顺时针方向设置每个四边形点的坐标值，求法向量
        QuadArray SurfaceQuadArray=new QuadArray(5*n0*4,
                GeometryArray.COORDINATES|GeometryArray.NORMALS);
        c=0;//该变量用来对顶点按顺序编号，该编号是连续的，不能重复
        for(i=0;i<4;i++)
        {for(j=0;j<n0;j++)
        {Point3f A=new Point3f(SurfacePointsxyz[i][j][0],
                SurfacePointsxyz[i][j][1],SurfacePointsxyz[i][j][2]);
            Point3f B=new Point3f(SurfacePointsxyz[i+1][j][0],
                    SurfacePointsxyz[i+1][j][1],SurfacePointsxyz[i+1][j][2]);
            Point3f C=new Point3f(SurfacePointsxyz[i+1][j+1][0],
                    SurfacePointsxyz[i+1][j+1][1],SurfacePointsxyz[i+1][j+1][2]);
            Point3f D=new Point3f(SurfacePointsxyz[i][j+1][0],
                    SurfacePointsxyz[i][j+1][1],SurfacePointsxyz[i][j+1][2]);
//计算四个点的法向量，使法向量指向体外
            Vector3f a = new Vector3f(A.x - B.x, A.y - B.y, A.z - B.z);
            Vector3f b = new Vector3f(C.x - B.x, C.y - B.y, C.z - B.z);
            Vector3f n= new Vector3f();
            n.cross(b, a);
            n.normalize();
//设置点的序号
            SurfaceQuadArray.setCoordinate(c, A);
            SurfaceQuadArray.setCoordinate(c+1, B);
            SurfaceQuadArray.setCoordinate(c+2, C);
            SurfaceQuadArray.setCoordinate(c+3, D);
//设置点的序号所对应的法向量
            SurfaceQuadArray.setNormal(c, n);
            SurfaceQuadArray.setNormal(c+1, n);
            SurfaceQuadArray.setNormal(c+2, n);
            SurfaceQuadArray.setNormal(c+3, n);
            c=c+4;
        }}
        GeometryInfo gi = new GeometryInfo(SurfaceQuadArray);
        NormalGenerator ng = new NormalGenerator();
        ng.generateNormals(gi);
        return gi.getGeometryArray();
    }}
//定义MorphingBehavior类
class MorphingBehavior extends Behavior
{ Morph morph;  Alpha alpha;
    public MorphingBehavior(Alpha a,Morph m){ morph = m; alpha = a; }
    public void initialize(){wakeupOn(new WakeupOnElapsedFrames(100));}
    public void processStimulus(java.util.Enumeration enumeration)
    {  double[] w = new double[4];
        double a = alpha.value();
        w[0] = 0; w[1] = 0;  w[2] = 0;  w[3] = 0;
        int index = (int)(a*3);
        if (index > 2) index = 2;
        w[index+1] = (a-index/3.0)*3;
        w[index] = 1.0-w[index+1];
        morph.setWeights(w);
        wakeupOn(new WakeupOnElapsedFrames(100)); }}
