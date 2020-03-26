import java.applet.Applet;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.universe.*;
import javax.imageio.ImageIO;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.behaviors.mouse.*;

public class Helicopter extends Applet
{public BranchGroup createBranchGroupSceneGraph()
{BranchGroup BranchGroupRoot =new BranchGroup();
    BoundingSphere bounds=new BoundingSphere(new Point3d(0.0,0.0,0.0),100.0);
    //定义背景
    URL url=getClass().getClassLoader().getResource("sky.png");
    BufferedImage bi=null;
    try{bi= ImageIO.read(url);}
    catch (IOException ex){ex.printStackTrace();}
    ImageComponent2D image=new ImageComponent2D(ImageComponent2D.FORMAT_RGB,bi);
    Background bg=new Background(image);
    bg.setApplicationBounds(bounds);
    BranchGroupRoot.addChild(bg);

    Color3f directionalColor=new Color3f(1.0f,1.0f,1.0f);
    Vector3f vec=new Vector3f(0.f,0.f,-1.0f);
    DirectionalLight directionalLight=new DirectionalLight(directionalColor,vec);
    directionalLight.setInfluencingBounds(bounds);
    BranchGroupRoot.addChild(directionalLight);
    Transform3D tr=new Transform3D();
    tr.setScale(0.85);
    TransformGroup transformgroup=new TransformGroup(tr);
    transformgroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    transformgroup.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    BranchGroupRoot.addChild(transformgroup);

    //定义MediaContainer类的相关内容
    MediaContainer media=new MediaContainer();
    URL url2=getClass().getClassLoader().getResource("sound.wav");
    media.setURLObject(url2);
    //定义BackgroundSound类的相关内容
    BackgroundSound bs=new BackgroundSound();
    bs.setLoop(-1);
    bs.setInitialGain(0.7f);
    bs.setSchedulingBounds(bounds);
    bs.setSoundData(media);
    bs.setEnable(true);
    transformgroup.addChild(bs);

    MouseRotate mouserotate = new MouseRotate();
    mouserotate.setTransformGroup(transformgroup);
    BranchGroupRoot.addChild(mouserotate);
    mouserotate.setSchedulingBounds(bounds);
    MouseZoom mousezoom = new MouseZoom();
    mousezoom.setTransformGroup(transformgroup);
    BranchGroupRoot.addChild(mousezoom);
    mousezoom.setSchedulingBounds(bounds);
    MouseTranslate mousetranslate = new MouseTranslate();
    mousetranslate.setTransformGroup(transformgroup);
    BranchGroupRoot.addChild(mousetranslate);
    mousetranslate.setSchedulingBounds(bounds);

    //定义外观app1-4
    Appearance app1 = new Appearance();
    PolygonAttributes polygona1=new PolygonAttributes();
    polygona1.setBackFaceNormalFlip(true);
    polygona1.setCullFace(PolygonAttributes.CULL_NONE);
    //polygona1.setPolygonMode(PolygonAttributes.POLYGON_LINE);
    app1.setPolygonAttributes(polygona1);
    ColoringAttributes color1=new ColoringAttributes();
    color1.setColor(0.f,0.f,0.f);
    app1.setColoringAttributes(color1);
    Material material1=new Material();
    material1.setDiffuseColor(new Color3f(0.2f,0.2f,0.2f) );
    app1.setMaterial(material1);

    Appearance app2 = new Appearance();
    PolygonAttributes polygona2=new PolygonAttributes();
    polygona2.setBackFaceNormalFlip(true);
    polygona2.setCullFace(PolygonAttributes.CULL_NONE);
    //polygona2.setPolygonMode(PolygonAttributes.POLYGON_LINE);
    app2.setPolygonAttributes(polygona2);
    ColoringAttributes color2=new ColoringAttributes();
    color2.setColor(0.f,0.f,0.f);
    app2.setColoringAttributes(color2);
    Material material2=new Material();
    material2.setDiffuseColor(new Color3f(1.0f,1.f,1.f) );
    app2.setMaterial(material2);

    Appearance app3 = new Appearance();
    PolygonAttributes polygona3=new PolygonAttributes();
    polygona3.setBackFaceNormalFlip(true);
    polygona3.setCullFace(PolygonAttributes.CULL_NONE);
    //polygona3.setPolygonMode(PolygonAttributes.POLYGON_LINE);
    app3.setPolygonAttributes(polygona3);
    ColoringAttributes color3=new ColoringAttributes();
    color3.setColor(1.f,0.f,0.f);
    app3.setColoringAttributes(color3);
    Material material3=new Material();
    material3.setDiffuseColor(new Color3f(0.8f,0.8f,0.8f) );
    app3.setMaterial(material3);

    Appearance app4 = new Appearance();
    ColoringAttributes color4=new ColoringAttributes();
    color4.setColor(1.f,1.f,0.f);
    app4.setColoringAttributes(color4);
    Material material4=new Material();
    material4.setDiffuseColor(new Color3f(0.5f,0.5f,0.5f) );
    app4.setMaterial(material4);

//左中面板下
    float[][][] P1={
                   {{-0.3f,0.1f,0.1f,1.f},
                    {-0.2f,0.1f,0.1f,1.f},
                    {-0.1f,0.1f,0.1f,1.f},
                    {0.f,0.1f,0.1f,1.f}  },

                 {  {-0.3f,0.075f,0.1f,1.f},
                    {-0.2f,0.075f,0.14f,1.f},
                    {-0.1f,0.075f,0.14f,1.f},
                    {0.f,0.075f,0.1f,1.f} },


                    {{-0.3f,0.01f,0.1f,1.f},
                    {-0.2f,0.01f,0.14f,1.f},
                    {-0.1f,0.01f,0.14f,1.f},
                    {0.f,0.01f,0.1f,1.f}   },

                  { {-0.3f,0.f,0.1f,1.f},
                    {-0.2f,0.f,0.1f,1.f},
                    {-0.1f,0.f,0.1f,1.f},
                    {-0.f,-0.f,0.1f,1.f} } };

    //右中面板下
    float[][][] P5={
            {{-0.3f,0.1f,-0.1f,1.f},
                    {-0.2f,0.1f,-0.1f,1.f},
                    {-0.1f,0.1f,-0.1f,1.f},
                    {0.f,0.1f,-0.1f,1.f}  },

            {  {-0.3f,0.075f,-0.1f,1.f},
                    {-0.2f,0.075f,-0.14f,1.f},
                    {-0.1f,0.075f,-0.14f,1.f},
                    {0.f,0.075f,-0.1f,1.f} },


            {{-0.3f,0.01f,-0.1f,1.f},
                    {-0.2f,0.01f,-0.14f,1.f},
                    {-0.1f,0.01f,-0.14f,1.f},
                    {0.f,0.01f,-0.1f,1.f}   },

            { {-0.3f,0.f,-0.1f,1.f},
                    {-0.2f,0.f,-0.1f,1.f},
                    {-0.1f,0.f,-0.1f,1.f},
                    {-0.f,-0.f,-0.1f,1.f} } };

//左中面板上
    float[][][] P2={
                   {{-0.38f,0.2f,0.03f,1.f},
                    {-0.2f,0.2f,0.03f,1.f},
                    {-0.f,0.2f,0.03f,1.f},
                    {0.2f,0.2f,0.03f,1.f}  },

                 {  {-0.3f,0.19f,0.1f,1.f},
                    {-0.2f,0.19f,0.1f,1.f},
                    {-0.f,0.19f,0.1f,1.f},
                    {0.2f,0.19f,0.1f,1.f} },


                   {{-0.3f,0.125f,0.1f,1.f},
                    {-0.2f,0.125f,0.1f,1.f},
                    {-0.f,0.125f,0.1f,1.f},
                    {0.2f,0.125f,0.1f,1.f}   },

                  { {-0.3f,0.1f,0.1f,1.f},
                    {-0.2f,0.1f,0.1f,1.f},
                    {-0.f,0.1f,0.1f,1.f},
                    {0.2f,0.1f,0.1f,1.f} } };

    //右中面板上
    float[][][] P6={
            {{-0.38f,0.2f,-0.03f,1.f},
                    {-0.2f,0.2f,-0.03f,1.f},
                    {-0.f,0.2f,-0.03f,1.f},
                    {0.2f,0.2f,-0.03f,1.f}  },

            {  {-0.3f,0.19f,-0.1f,1.f},
                    {-0.2f,0.19f,-0.1f,1.f},
                    {-0.f,0.19f,-0.1f,1.f},
                    {0.2f,0.19f,-0.1f,1.f} },


            {{-0.3f,0.125f,-0.1f,1.f},
                    {-0.2f,0.125f,-0.1f,1.f},
                    {-0.f,0.125f,-0.1f,1.f},
                    {0.2f,0.125f,-0.1f,1.f}   },

            { {-0.3f,0.1f,-0.1f,1.f},
                    {-0.2f,0.1f,-0.1f,1.f},
                    {-0.f,0.1f,-0.1f,1.f},
                    {0.2f,0.1f,-0.1f,1.f} } };
    //玻璃
    float[][][] P3={ {{-0.38f,0.2f,-0.03f,1.f},
                     {-0.4f,0.2f,-0.025f,1.f},
                     {-0.4f,0.2f,0.025f,1.f},
                     {-0.38f,0.2f,0.03f,1.f}  },

                 {  {-0.3f,0.19f,-0.1f,1.f},
                    {-0.45f,0.19f,-0.05f,1.f},
                    {-0.45f,0.19f,0.05f,1.f},
                    {-0.3f,0.19f,0.1f,1.f}},


                   {{-0.3f,0.125f,-0.1f,1.f},
                    {-0.51f,0.125f,-0.05f,1.f},
                    {-0.51f,0.125f,0.05f,1.f},
                    {-0.3f,0.125f,0.1f,1.f}   },

                  { {-0.3f,0.1f,-0.1f,1.f},
                    {-0.5f,0.1f,0.f,1.f},
                    {-0.5f,0.1f,0.f,1.f},
                     {-0.3f,0.1f,0.1f,1.f} }};


    //机头左
    float[][][] P4={{{-0.6f,0.075f,0.f,1.f},
                     {-0.53f,0.1f,0.06f,1.f},
                     {-0.51f,0.1f,0.1f,1.f},
                     {-0.3f,0.1f,0.1f,1.f}  },

                 {  {-0.6f,0.075f,0.f,1.f},
                    {-0.53f,0.075f,0.06f,1.f},
                    {-0.51f,0.075f,0.1f,1.f},
                    {-0.3f,0.075f,0.1f,1.f} },


                   {{-0.6f,0.05f,0.f,1.f},
                    {-0.53f,-0.01f,0.06f,1.f},
                    {-0.51f,-0.01f,0.1f,1.f},
                    {-0.3f,0.01f,0.1f,1.f}   },

                  { {-0.6f,0.05f,0.f,1.f},
                    {-0.53f,-0.f,0.06f,1.f},
                    {-0.5f,-0.f,0.1f,1.f},
                    {-0.3f,-0.f,0.1f,1.f} }};
//定义第四个Bezier曲面外观属性

    //机头右
    float[][][] P7={{{-0.6f,0.075f,-0.f,1.f},
            {-0.53f,0.1f,-0.06f,1.f},
            {-0.51f,0.1f,-0.1f,1.f},
            {-0.3f,0.1f,-0.1f,1.f}  },

            {  {-0.6f,0.075f,-0.f,1.f},
                    {-0.53f,0.075f,-0.06f,1.f},
                    {-0.51f,0.075f,-0.1f,1.f},
                    {-0.3f,0.075f,-0.1f,1.f} },


            {{-0.6f,0.05f,-0.f,1.f},
                    {-0.53f,-0.01f,-0.06f,1.f},
                    {-0.51f,-0.01f,-0.1f,1.f},
                    {-0.3f,0.01f,-0.1f,1.f}   },

            { {-0.6f,0.05f,-0.f,1.f},
                    {-0.53f,-0.f,-0.06f,1.f},
                    {-0.5f,-0.f,-0.1f,1.f},
                    {-0.3f,-0.f,-0.1f,1.f} }};
    //机头下
    float[][][] P8={{{-0.6f,0.05f,0.f,1.f},
                     {-0.53f,-0.f,0.06f,1.f},
                     {-0.5f,-0.f,0.1f,1.f},
                     {-0.3f,-0.f,0.1f,1.f}  },

                 {  {-0.6f,0.05f,-0.f,1.f},
                    {-0.53f,0.0f,-0.06f,1.f},
                    {-0.51f,0.0f,-0.1f,1.f},
                    {-0.3f,-0.f,0.1f,1.f} },


                    {{-0.6f,0.05f,-0.f,1.f},
                    {-0.53f,-0.0f,-0.06f,1.f},
                    {-0.51f,-0.0f,-0.1f,1.f},
                    {-0.3f,-0.f,0.1f,1.f}   },

                  { {-0.6f,0.05f,-0.f,1.f},
                    {-0.53f,-0.f,-0.06f,1.f},
                    {-0.5f,-0.f,-0.1f,1.f},
                    {-0.3f,-0.f,-0.1f,1.f} }};

//机头上
    float[][][] P9={{
            {-0.3f,0.1f,-0.1f,1.f},
            {-0.5f,0.1f,0.f,1.f},
            {-0.5f,0.1f,0.f,1.f},
            {-0.3f,0.1f,0.1f,1.f}  },

           {{-0.51f,0.1f,-0.1f,1.f},
            {-0.51f,0.13f,-0.06f,1.f},
            {-0.51f,0.13f,0.06f,1.f},
            {-0.51f,0.1f,0.1f,1.f} },


            {{-0.53f,0.1f,-0.06f,1.f},
            {-0.51f,0.13f,-0.06f,1.f},
            {-0.51f,0.13f,0.06f,1.f},
            {-0.53f,0.1f,0.06f,1.f}   },

            { {-0.6f,0.075f,-0.f,1.f},
            {-0.6f,0.075f,-0.f,1.f},
            {-0.6f,0.075f,-0.f,1.f},
            {-0.6f,0.075f,-0.f,1.f} }};
    //下面板中
    float[][][] P10={
        {{-0.3f,0.f,0.1f,1.f},
                {-0.2f,0.f,0.1f,1.f},
                {-0.1f,0.f,0.1f,1.f},
                {-0.f,-0.f,0.1f,1.f}  },

        {  {-0.3f,0.f,0.05f,1.f},
                {-0.2f,0.f,0.05f,1.f},
                {-0.1f,0.f,0.05f,1.f},
                {-0.f,-0.f,0.05f,1.f} },


        {{-0.3f,0.f,-0.05f,1.f},
                {-0.2f,0.f,-0.05f,1.f},
                {-0.1f,0.f,-0.05f,1.f},
                {-0.f,-0.f,-0.05f,1.f}   },

        { {-0.3f,0.f,-0.1f,1.f},
                {-0.2f,0.f,-0.1f,1.f},
                {-0.1f,0.f,-0.1f,1.f},
                {-0.f,-0.f,-0.1f,1.f} } };
//后下面板左
    float[][][] P11={
                    {{0.f,0.1f,0.1f,1.f} ,
                    {0.06f,0.1f,0.1f,1.f},
                    {0.12f,0.1f,0.1f,1.f},
                    {0.2f,0.1f,0.1f,1.f}  },

                 {  {0.f,0.075f,0.1f,1.f},
                    {0.06f,0.075f,0.1f,1.f},
                    {0.12f,0.075f,0.1f,1.f},
                    {0.2f,0.1f,0.1f,1.f} },


                    {{0.f,0.01f,0.1f,1.f},
                    {0.06f,0.01f,0.1f,1.f},
                    {0.12f,0.01f,0.1f,1.f},
                    {0.2f,0.1f,0.1f,1.f}   },

                  { {0.f,-0.f,0.1f,1.f},
                    {0.06f,0.f,0.1f,1.f},
                    {0.12f,0.f,0.1f,1.f},
                    {0.2f,0.1f,0.1f,1.f} } };
    //后下面板右
    float[][][] P12={
                   {{0.f,0.1f,-0.1f,1.f} ,
                    {0.06f,0.1f,-0.1f,1.f},
                    {0.12f,0.1f,-0.1f,1.f},
                    {0.2f,0.1f,-0.1f,1.f}  },

            {  {0.f,0.075f,-0.1f,1.f},
                    {0.06f,0.075f,-0.1f,1.f},
                    {0.12f,0.075f,-0.1f,1.f},
                    {0.2f,0.1f,-0.1f,1.f} },


            {{0.f,0.01f,-0.1f,1.f},
                    {0.06f,0.01f,-0.1f,1.f},
                    {0.12f,0.01f,-0.1f,1.f},
                    {0.2f,0.1f,-0.1f,1.f}   },

            { {0.f,-0.f,-0.1f,1.f},
                    {0.06f,0.f,-0.1f,1.f},
                    {0.12f,0.f,-0.1f,1.f},
                    {0.2f,0.1f,-0.1f,1.f} } };

//后下面板
    float[][][] P13={
                  {{0.f,-0.f,0.1f,1.f},
                    {0.06f,0.f,0.1f,1.f},
                    {0.12f,0.f,0.1f,1.f},
                    {0.2f,0.1f,0.1f,1.f}  },

                 {  {0.f,0.f,0.05f,1.f} ,
                    {0.06f,0.f,0.05f,1.f},
                    {0.12f,0.f,0.05f,1.f},
                    {0.2f,0.1f,0.05f,1.f} },


                   {{0.f,0.f,-0.05f,1.f} ,
                    {0.06f,0.f,-0.05f,1.f},
                    {0.12f,0.f,-0.05f,1.f},
                    {0.2f,0.1f,-0.05f,1.f}   },

                  { {0.f,-0.f,-0.1f,1.f},
                    {0.06f,0.f,-0.1f,1.f},
                    {0.12f,0.f,-0.1f,1.f},
                    {0.2f,0.1f,-0.1f,1.f}} };
    //机后体左
    float[][][] P14={
                   {{0.2f,0.2f,0.03f,1.f},
                    {0.4f,0.2f,0.01f,1.f},
                    {0.6f,0.2f,0.f,1.f},
                    {0.8f,0.2f,0.f,1.f}  },

                 {  {0.2f,0.19f,0.1f,1.f},
                    {0.4f,0.19f,0.075f,1.f},
                    {0.6f,0.19f,0.025f,1.f},
                    {0.8f,0.2f,0.f,1.f} },


                   {{0.2f,0.125f,0.1f,1.f},
                    {0.4f,0.125f,0.075f,1.f},
                    {0.6f,0.175f,0.025f,1.f},
                    {0.8f,0.2f,0.f,1.f}  },

                  { {0.2f,0.1f,0.1f,1.f},
                    {0.4f,0.125f,0.075f,1.f},
                    {0.6f,0.175f,0.025f,1.f},
                    {0.8f,0.2f,0.f,1.f} } };
//机后体右
    float[][][] P15={
            {{0.2f,0.2f,-0.03f,1.f},
                    {0.4f,0.2f,-0.01f,1.f},
                    {0.6f,0.2f,-0.f,1.f},
                    {0.8f,0.2f,-0.f,1.f}  },

            {  {0.2f,0.19f,-0.1f,1.f},
                    {0.4f,0.19f,-0.075f,1.f},
                    {0.6f,0.19f,-0.025f,1.f},
                    {0.8f,0.2f,-0.f,1.f} },


            {{0.2f,0.125f,-0.1f,1.f},
                    {0.4f,0.125f,-0.075f,1.f},
                    {0.6f,0.175f,-0.025f,1.f},
                    {0.8f,0.2f,-0.f,1.f}  },

            { {0.2f,0.1f,-0.1f,1.f},
                    {0.4f,0.125f,-0.075f,1.f},
                    {0.6f,0.175f,-0.025f,1.f},
                    {0.8f,0.2f,-0.f,1.f} } };
//机后体上
    float[][][] P16={
            {{0.2f,0.2f,-0.03f,1.f},
                    {0.4f,0.2f,-0.01f,1.f},
                    {0.6f,0.2f,-0.f,1.f},
                    {0.8f,0.2f,-0.f,1.f}  },

            {  {0.2f,0.19f,0.1f,1.f},
                    {0.4f,0.19f,0.075f,1.f},
                    {0.6f,0.19f,0.025f,1.f},
                    {0.8f,0.2f,0.f,1.f} },


            {{0.2f,0.125f,0.1f,1.f},
                    {0.4f,0.125f,0.075f,1.f},
                    {0.6f,0.175f,0.025f,1.f},
                    {0.8f,0.2f,0.f,1.f}  },

            { {0.2f,0.2f,0.03f,1.f},
                    {0.4f,0.2f,0.01f,1.f},
                    {0.6f,0.2f,0.f,1.f},
                    {0.8f,0.2f,0.f,1.f}  } };
//机后体下
    float[][][] P17={
                   {{0.2f,0.1f,0.1f,1.f},
                    {0.4f,0.125f,0.075f,1.f},
                    {0.6f,0.175f,0.025f,1.f},
                    {0.8f,0.2f,0.f,1.f}  },

                 {  {0.2f,0.1f,0.05f,1.f},
                    {0.4f,0.125f,0.075f,1.f},
                    {0.6f,0.125f,0.025f,1.f},
                    {0.8f,0.2f,0.f,1.f} },


                   {{0.2f,0.1f,-0.05f,1.f},
                    {0.4f,0.125f,0.075f,1.f},
                    {0.6f,0.175f,0.025f,1.f},
                    {0.8f,0.2f,0.f,1.f}  },

                  { {0.2f,0.1f,-0.1f,1.f},
                    {0.4f,0.125f,-0.075f,1.f},
                    {0.6f,0.175f,-0.025f,1.f},
                    {0.8f,0.2f,-0.f,1.f} } };
//机身顶
    float[][][] P18={
                   {{-0.38f,0.2f,0.03f,1.f},
                    {-0.2f,0.2f,0.03f,1.f},
                    {-0.f,0.2f,0.03f,1.f},
                    {0.2f,0.2f,0.03f,1.f}  },

                  { {-0.4f,0.2f,0.025f,1.f},
                    {-0.2f,0.3f,0.025f,1.f},
                    {-0.f,0.3f,0.025f,1.f},
                    {0.2f,0.2f,0.025f,1.f}  },


                   {{-0.4f,0.2f,-0.025f,1.f},
                    {-0.2f,0.3f,-0.025f,1.f},
                    {-0.f,0.3f,-0.025f,1.f},
                    {0.2f,0.2f,-0.025f,1.f} },

                  { {-0.38f,0.2f,-0.03f,1.f},
                    {-0.2f,0.2f,-0.03f,1.f},
                    {-0.f,0.2f,-0.03f,1.f},
                    {0.2f,0.2f,-0.03f,1.f} } };

    //机尾
    float[][][] P19={
                   {{0.75f,0.3f,0.0f,1.f},
                    {0.8f,0.3f,0.0f,1.f},
                    {0.85f,0.3f,0.0f,1.f},
                    {0.9f,0.3f,0.0f,1.f}  },

                  { {0.716f,0.266f,0.0f,1.f},
                    {0.766f,0.266f,0.0f,1.f},
                    {0.816f,0.266f,0.0f,1.f},
                    {0.866f,0.266f,0.0f,1.f}  },


                   {{0.683f,0.233f,-0.0f,1.f},
                    {0.713f,0.233f,-0.0f,1.f},
                    {0.763f,0.233f,-0.0f,1.f},
                    {0.813f,0.233f,-0.0f,1.f} },

                  { {0.65f,0.2f,-0.f,1.f},
                    {0.7f,0.2f,-0.f,1.f},
                    {0.75f,0.2f,-0.f,1.f},
                    {0.8f,0.2f,-0.f,1.f} } };
//机尾右
    float[][][] P24={
                   {{0.75f,0.2f,-0.1f,1.f},
                    {0.8f,0.2f,-0.1f,1.f},
                    {0.85f,0.2f,-0.1f,1.f},
                    {0.9f,0.2f,-0.1f,1.f}  },

                  { {0.716f,0.2f,-0.066f,1.f},
                    {0.766f,0.2f,-0.066f,1.f},
                    {0.816f,0.2f,-0.066f,1.f},
                    {0.866f,0.2f,-0.066f,1.f}  },


                   {{0.683f,0.2f,-0.033f,1.f},
                    {0.713f,0.2f,-0.033f,1.f},
                    {0.763f,0.2f,-0.033f,1.f},
                    {0.813f,0.2f,-0.033f,1.f} },

                  { {0.65f,0.2f,-0.f,1.f},
                    {0.7f,0.2f,-0.f,1.f},
                    {0.75f,0.2f,-0.f,1.f},
                    {0.8f,0.2f,-0.f,1.f} } };
//机尾左
    float[][][] P25={
                   {{0.75f,0.2f,0.1f,1.f},
                    {0.8f,0.2f,0.1f,1.f},
                    {0.85f,0.2f,0.1f,1.f},
                    {0.9f,0.2f,0.1f,1.f}  },

                  { {0.716f,0.2f,0.066f,1.f},
                    {0.766f,0.2f,0.066f,1.f},
                    {0.816f,0.2f,0.066f,1.f},
                    {0.866f,0.2f,0.066f,1.f}  },


                   {{0.683f,0.2f,0.033f,1.f},
                    {0.713f,0.2f,0.033f,1.f},
                    {0.763f,0.2f,0.033f,1.f},
                    {0.813f,0.2f,0.033f,1.f} },

                  { {0.65f,0.2f,-0.f,1.f},
                    {0.7f,0.2f,-0.f,1.f},
                    {0.75f,0.2f,-0.f,1.f},
                    {0.8f,0.2f,-0.f,1.f} } };

    //螺旋桨1
    float[][][] P20={
                   {{-0.11f,0.258f,0.03f,1.f},
                    {-0.103f,0.262f,0.03f,1.f},
                    {-0.097f,0.266f,0.03f,1.f},
                    {-0.09f,0.27f,0.03f,1.f}  },

                  { {-0.117f,0.258f,0.23f,1.f},
                    {-0.102f,0.262f,0.23f,1.f},
                    {-0.098f,0.266f,0.23f,1.f},
                    {-0.083f,0.27f,0.23f,1.f}  },


                   {{-0.123f,0.258f,0.43f,1.f},
                    {-0.108f,0.262f,0.43f,1.f},
                    {-0.092f,0.266f,0.43f,1.f},
                    {-0.077f,0.27f,0.43f,1.f} },

                  { {-0.13f,0.258f,0.63f,1.f},
                    {-0.113f,0.262f,0.63f,1.f},
                    {-0.09f,0.266f,0.63f,1.f},
                    {-0.07f,0.27f,0.63f,1.f} } };

    //螺旋桨2
    float[][][] P21={
                   {{-0.11f,0.27f,-0.03f,1.f},
                    {-0.103f,0.266f,-0.03f,1.f},
                    {-0.097f,0.262f,-0.03f,1.f},
                    {-0.09f,0.258f,-0.03f,1.f}  },

                  { {-0.117f,0.27f,-0.23f,1.f},
                    {-0.102f,0.266f,-0.23f,1.f},
                    {-0.098f,0.262f,-0.23f,1.f},
                    {-0.083f,0.258f,-0.23f,1.f}  },


                   {{-0.123f,0.27f,-0.43f,1.f},
                    {-0.108f,0.266f,-0.43f,1.f},
                    {-0.092f,0.262f,-0.43f,1.f},
                    {-0.077f,0.258f,-0.43f,1.f} },

                  { {-0.13f,0.27f,-0.63f,1.f},
                    {-0.113f,0.266f,-0.63f,1.f},
                    {-0.09f,0.262f,-0.63f,1.f},
                    {-0.07f,0.258f,-0.63f,1.f} } };

    //螺旋桨3
    float[][][] P22={
                   {{-0.07f,0.258f,0.01f,1.f},
                    {-0.07f,0.262f,0.003f,1.f},
                    {-0.07f,0.266f,-0.003f,1.f},
                    {-0.07f,0.27f,-0.01f,1.f}  },

                  { {0.27f,0.258f,0.016f,1.f},
                    {0.27f,0.262f,0.016f,1.f},
                    {0.27f,0.266f,-0.016f,1.f},
                    {0.27f,0.27f,-0.016f,1.f}  },


                   {{0.47f,0.258f,0.023f,1.f},
                    {0.47f,0.262f,0.023f,1.f},
                    {0.47f,0.266f,-0.023f,1.f},
                    {0.47f,0.27f,-0.023f,1.f} },

                  { {0.67f,0.258f,0.03f,1.f},
                    {0.67f,0.262f,0.01f,1.f},
                    {0.67f,0.266f,-0.01f,1.f},
                    {0.67f,0.27f,-0.03f,1.f} } };

//螺旋桨4
    float[][][] P23={
                   {{-0.13f,0.27f,0.01f,1.f},
                    {-0.13f,0.266f,0.003f,1.f},
                    {-0.13f,0.262f,-0.003f,1.f},
                    {-0.13f,0.258f,-0.01f,1.f}  },

                  { {-0.33f,0.27f,0.016f,1.f},
                    {-0.33f,0.266f,0.016f,1.f},
                    {-0.33f,0.262f,-0.016f,1.f},
                    {-0.33f,0.258f,-0.016f,1.f}  },


                   {{-0.53f,0.27f,0.023f,1.f},
                    {-0.53f,0.266f,0.023f,1.f},
                    {-0.53f,0.262f,-0.023f,1.f},
                    {-0.53f,0.258f,-0.023f,1.f} },

                  { {-0.73f,0.27f,0.03f,1.f},
                    {-0.73f,0.266f,0.01f,1.f},
                    {-0.73f,0.262f,-0.01f,1.f},
                    {-0.73f,0.258f,-0.03f,1.f} } };
//尾桨1
    float[][][] P26={
                   {{0.775f,0.3f,0.01f,1.f},
                    {0.775f,0.3f,0.01f,1.f},
                    {0.775f,0.3f,0.01f,1.f},
                    {0.775f,0.3f,0.01f,1.f}  },

                  { {0.765f,0.29f,0.01f,1.f},
                    {0.765f,0.29f,0.01f,1.f},
                    {0.785f,0.29f,0.01f,1.f},
                    {0.785f,0.29f,0.01f,1.f}  },

                   {{0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f}  },

                   {{0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f}  },};
//尾桨2
    float[][][] P27={
                   {{0.775f,0.2f,0.01f,1.f},
                    {0.775f,0.2f,0.01f,1.f},
                    {0.775f,0.2f,0.01f,1.f},
                    {0.775f,0.2f,0.01f,1.f}  },

                  { {0.765f,0.21f,0.01f,1.f},
                    {0.765f,0.21f,0.01f,1.f},
                    {0.785f,0.21f,0.01f,1.f},
                    {0.785f,0.21f,0.01f,1.f}  },

                   {{0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f}  },

                   {{0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f}  },};
//尾桨3
    float[][][] P28={
                   {{0.725f,0.25f,0.01f,1.f},
                    {0.725f,0.25f,0.01f,1.f},
                    {0.725f,0.25f,0.01f,1.f},
                    {0.725f,0.25f,0.01f,1.f}  },

                  { {0.735f,0.24f,0.01f,1.f},
                    {0.735f,0.24f,0.01f,1.f},
                    {0.735f,0.26f,0.01f,1.f},
                    {0.735f,0.26f,0.01f,1.f}  },

                   {{0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f}  },

                   {{0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f}  },};
//尾桨4
    float[][][] P29={
                   {{0.825f,0.25f,0.01f,1.f},
                    {0.825f,0.25f,0.01f,1.f},
                    {0.825f,0.25f,0.01f,1.f},
                    {0.825f,0.25f,0.01f,1.f}  },

                  { {0.815f,0.24f,0.01f,1.f},
                    {0.815f,0.24f,0.01f,1.f},
                    {0.815f,0.26f,0.01f,1.f},
                    {0.815f,0.26f,0.01f,1.f}  },

                   {{0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f}  },

                   {{0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f},
                    {0.775f,0.25f,0.01f,1.f}  },};

    Shape3D BezierSurfaceface1=new BezierThreeOrderSurfaceface(P1,app1);
    transformgroup.addChild(BezierSurfaceface1);
    Shape3D BezierSurfaceface2=new BezierThreeOrderSurfaceface(P2,app1);
    transformgroup.addChild(BezierSurfaceface2);
    Shape3D BezierSurfaceface3=new BezierThreeOrderSurfaceface(P3,app2);
    transformgroup.addChild(BezierSurfaceface3);
    Shape3D BezierSurfaceface4=new BezierThreeOrderSurfaceface(P4,app1);
    transformgroup.addChild(BezierSurfaceface4);
    Shape3D BezierSurfaceface5=new BezierThreeOrderSurfaceface(P5,app1);
    transformgroup.addChild(BezierSurfaceface5);
    Shape3D BezierSurfaceface6=new BezierThreeOrderSurfaceface(P6,app1);
    transformgroup.addChild(BezierSurfaceface6);
    Shape3D BezierSurfaceface7=new BezierThreeOrderSurfaceface(P7,app1);
    transformgroup.addChild(BezierSurfaceface7);
    Shape3D BezierSurfaceface8=new BezierThreeOrderSurfaceface(P8,app1);
    transformgroup.addChild(BezierSurfaceface8);
    Shape3D BezierSurfaceface9=new BezierThreeOrderSurfaceface(P9,app1);
    transformgroup.addChild(BezierSurfaceface9);
    Shape3D BezierSurfaceface10=new BezierThreeOrderSurfaceface(P10,app1);
    transformgroup.addChild(BezierSurfaceface10);
    Shape3D BezierSurfaceface11=new BezierThreeOrderSurfaceface(P11,app1);
    transformgroup.addChild(BezierSurfaceface11);
    Shape3D BezierSurfaceface12=new BezierThreeOrderSurfaceface(P12,app1);
    transformgroup.addChild(BezierSurfaceface12);
    Shape3D BezierSurfaceface13=new BezierThreeOrderSurfaceface(P13,app1);
    transformgroup.addChild(BezierSurfaceface13);
    Shape3D BezierSurfaceface14=new BezierThreeOrderSurfaceface(P14,app1);
    transformgroup.addChild(BezierSurfaceface14);
    Shape3D BezierSurfaceface15=new BezierThreeOrderSurfaceface(P15,app1);
    transformgroup.addChild(BezierSurfaceface15);
    Shape3D BezierSurfaceface16=new BezierThreeOrderSurfaceface(P16,app1);
    transformgroup.addChild(BezierSurfaceface16);
    Shape3D BezierSurfaceface17=new BezierThreeOrderSurfaceface(P17,app1);
    transformgroup.addChild(BezierSurfaceface17);
    Shape3D BezierSurfaceface18=new BezierThreeOrderSurfaceface(P18,app1);
    transformgroup.addChild(BezierSurfaceface18);
    Shape3D BezierSurfaceface19=new BezierThreeOrderSurfaceface(P19,app1);
    transformgroup.addChild(BezierSurfaceface19);
    Shape3D BezierSurfaceface20=new BezierThreeOrderSurfaceface(P20,app1);
    //transformgroup.addChild(BezierSurfaceface20);
    Shape3D BezierSurfaceface21=new BezierThreeOrderSurfaceface(P21,app1);
    //transformgroup.addChild(BezierSurfaceface21);
    Shape3D BezierSurfaceface22=new BezierThreeOrderSurfaceface(P22,app1);
    //transformgroup.addChild(BezierSurfaceface22);
    Shape3D BezierSurfaceface23=new BezierThreeOrderSurfaceface(P23,app1);
    //transformgroup.addChild(BezierSurfaceface23);
    Shape3D BezierSurfaceface24=new BezierThreeOrderSurfaceface(P24,app3);
    transformgroup.addChild(BezierSurfaceface24);
    Shape3D BezierSurfaceface25=new BezierThreeOrderSurfaceface(P25,app3);
    transformgroup.addChild(BezierSurfaceface25);
    Shape3D BezierSurfaceface26=new BezierThreeOrderSurfaceface(P26,app3);
    Shape3D BezierSurfaceface27=new BezierThreeOrderSurfaceface(P27,app3);
    Shape3D BezierSurfaceface28=new BezierThreeOrderSurfaceface(P28,app3);
    Shape3D BezierSurfaceface29=new BezierThreeOrderSurfaceface(P29,app3);



    //尾桨组
    TransformGroup tg7=new TransformGroup();
    tg7.addChild(BezierSurfaceface26);
    tg7.addChild(BezierSurfaceface27);
    tg7.addChild(BezierSurfaceface28);
    tg7.addChild(BezierSurfaceface29);
    tg7.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    tg7.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    transformgroup.addChild(tg7);


    //Shape3D BezierControlPoints1=new BezierSurfaceControlPoints(P1,app1);
    //transformgroup.addChild(BezierControlPoints1);

    //螺旋桨组
    TransformGroup tg=new TransformGroup();
    tg.addChild(BezierSurfaceface20);
    tg.addChild(BezierSurfaceface21);
    tg.addChild(BezierSurfaceface22);
    tg.addChild(BezierSurfaceface23);
    tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    transformgroup.addChild(tg);

    //螺旋桨中心
    Transform3D t=new Transform3D();
    t.setTranslation(new Vector3f(-0.1f,0.26f,0.f));
    TransformGroup tg1=new TransformGroup(t);
    tg1.addChild(new Cylinder(0.04f,0.05f,app4));
    transformgroup.addChild(tg1);

    //四个轮子
    t=new Transform3D();
    t.rotX(Math.PI/2);
    t.setTranslation(new Vector3f(-0.25f,0.f,0.1f));
    TransformGroup tg2=new TransformGroup(t);
    tg2.addChild(new Cylinder(0.04f,0.05f,app4));
    transformgroup.addChild(tg2);

    t=new Transform3D();
    t.rotX(Math.PI/2);
    t.setTranslation(new Vector3f(-0.05f,0.f,0.1f));
    TransformGroup tg3=new TransformGroup(t);
    tg3.addChild(new Cylinder(0.04f,0.05f,app4));
    transformgroup.addChild(tg3);

    t=new Transform3D();
    t.rotX(Math.PI/2);
    t.setTranslation(new Vector3f(-0.25f,0.f,-0.1f));
    TransformGroup tg4=new TransformGroup(t);
    tg4.addChild(new Cylinder(0.04f,0.05f,app4));
    transformgroup.addChild(tg4);

    t=new Transform3D();
    t.rotX(Math.PI/2);
    t.setTranslation(new Vector3f(-0.05f,0.f,-0.1f));
    TransformGroup tg5=new TransformGroup(t);
    tg5.addChild(new Cylinder(0.04f,0.05f,app4));
    transformgroup.addChild(tg5);

    //尾桨中心
    t=new Transform3D();
    t.rotX(Math.PI/2);
    t.setTranslation(new Vector3f(0.775f,0.25f,0.01f));
    TransformGroup tg6=new TransformGroup(t);
    tg6.addChild(new Cylinder(0.01f,0.01f,app4));
    transformgroup.addChild(tg6);
    
    //螺旋桨旋转
    Alpha rotationAlpha1=new Alpha(-1, Alpha.INCREASING_ENABLE,0,0,700,0,0,0,0,0);
    Transform3D zAxis1 = new Transform3D();
    zAxis1.setTranslation(new Vector3f(-0.1f,0.26f,0.f));
    RotationInterpolator rotator1=new RotationInterpolator(rotationAlpha1, tg, zAxis1,0.0f,(float)Math.PI*2.0f);
    rotator1.setSchedulingBounds(bounds);
    transformgroup.addChild(rotator1);

    //尾桨旋转
    Alpha rotationAlpha3=new Alpha(-1, Alpha.INCREASING_ENABLE,0,0,700,0,0,0,0,0);
    Transform3D zAxis3 = new Transform3D();
    zAxis3.rotX(Math.PI/2);
    zAxis3.setTranslation(new Vector3f(0.775f,0.25f,0.01f));
    RotationInterpolator rotator3=new RotationInterpolator(rotationAlpha3, tg7, zAxis3,0.0f,(float)Math.PI*2.0f);
    rotator3.setSchedulingBounds(bounds);
    transformgroup.addChild(rotator3);

    //整体旋转
    Alpha rotationAlpha2=new Alpha(-1,
            Alpha.DECREASING_ENABLE,0,0,7000,0,0,7000,0,0);
    Transform3D zAxis2 = new Transform3D();
    Vector3d Eulor1=new Vector3d(Math.PI/4,Math.PI/4,0.);
    zAxis2.setEuler(Eulor1);
        zAxis2.setTranslation(new Vector3f(-1.f,-1.f,-1.5f));
    RotationInterpolator rotator2=new RotationInterpolator(rotationAlpha2,
            transformgroup,zAxis2,0.0f,(float)Math.PI*2.0f);
    rotator2.setSchedulingBounds(bounds);
    //transformgroup.addChild(rotator2);

    BranchGroupRoot.compile();
    return BranchGroupRoot;
}
    public Helicopter()
    {setLayout(new BorderLayout());
        Panel p = new Panel();
        p.add(new Label("20151681310036 冯懿 复杂创意设计"));
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
    {new MainFrame(new Helicopter(),800,600); }
}

class  BezierThreeOrderSurfaceface extends Shape3D
{public BezierThreeOrderSurfaceface(float[][][] P,Appearance app)
{int i,j,k;
    int n0;//定义u,v在[0，1]区间的等分点数
    float division;//定义u,v在[0，1]区间的等分线段长度
    n0=50;division=1.f/n0;
//分别定义存放控制顶点x、y、z坐标与第四维的数组
    float[][] PX=new float[4][4];
    float[][] PY=new float[4][4];
    float[][] PZ=new float[4][4];
    float[][] P4=new float[4][4];
//定义系数矩阵及其专置矩阵
    float[][] M1={{1.f,0.f,0.f,0.f},
            {-3.f,3.f,0.f,0.f},
            {3.f,-6.f,3.f,0.f},
            {-1.f,3.f,-3.f,1.f}};
    float[][] M2={{1.f,-3.f,3.f,-1.f},
            {0.f,3.f,-6.f,3.f},
            {0.f,0.f,3.f,-3.f},
            {0.f,0.f,0.f,1.f}};
//定义存放Bezier曲面u,v参数分割点的坐标数组
    float[][][] UV=new float[n0+1][n0+1][2];
//定义u、v矩阵数组
    float[][] UU=new float[1][4];
    float[][] VV=new float[4][1];
//定义存放曲面上点的坐标的数组
    float[][][] SurfaceXYZ=new float[n0+1][n0+1][4];
    for(i=0;i<n0+1;i++)
        for(j=0;j<n0+1;j++)
        { 	UV[i][j][0]=i*division;
            UV[i][j][1]=j*division;   }
    for(i=0;i<4;i++)
        for(j=0;j<4;j++)
        { PX[i][j]=P[i][j][0];
            PY[i][j]=P[i][j][1];
            PZ[i][j]=P[i][j][2];
            P4[i][j]=P[i][j][3]; }
//计算曲面上所有点的坐标
    for(i=0;i<n0+1;i++)
        for(j=0;j<n0+1;j++)
        {   UU[0][0]=1.f;
            UU[0][1]=UV[i][j][0];
            UU[0][2]=UV[i][j][0]*UV[i][j][0];
            UU[0][3]=UV[i][j][0]*UV[i][j][0]*UV[i][j][0];
            VV[0][0]=1.f;
            VV[1][0]=UV[i][j][1];
            VV[2][0]=UV[i][j][1]*UV[i][j][1];
            VV[3][0]=UV[i][j][1]*UV[i][j][1]*UV[i][j][1];
            //计算一点的x坐标
            matrixm g0=new matrixm(1,4,4,UU,M1);
            matrixm g1=new matrixm(1,4,4,g0.CC,PX);
            matrixm g2=new matrixm(1,4,4,g1.CC,M2);
            matrixm g3=new matrixm(1,4,1,g2.CC,VV);
            SurfaceXYZ[i][j][0]=g3.CC[0][0];
            //计算一点的y坐标
            matrixm g4=new matrixm(1,4,4,UU,M1);
            matrixm g5=new matrixm(1,4,4,g4.CC,PY);
            matrixm g6=new matrixm(1,4,4,g5.CC,M2);
            matrixm g7=new matrixm(1,4,1,g6.CC,VV);
            SurfaceXYZ[i][j][1]=g7.CC[0][0];
            //计算一点的z坐标
            matrixm g8=new matrixm(1,4,4,UU,M1);
            matrixm g9=new matrixm(1,4,4,g8.CC,PZ);
            matrixm g10=new matrixm(1,4,4,g9.CC,M2);
            matrixm g11=new matrixm(1,4,1,g10.CC,VV);
            SurfaceXYZ[i][j][2]=g11.CC[0][0];
            //计算一点的第四维坐标
            matrixm g12=new matrixm(1,4,4,UU,M1);
            matrixm g13=new matrixm(1,4,4,g12.CC,P4);
            matrixm g14=new matrixm(1,4,4,g13.CC,M2);
            matrixm g15=new matrixm(1,4,1,g14.CC,VV);
            SurfaceXYZ[i][j][3]=g15.CC[0][0];
            //将齐次坐标转换为三位坐标系坐标，如果第四维为1，可不用除第四维
            SurfaceXYZ[i][j][0]=SurfaceXYZ[i][j][0]/SurfaceXYZ[i][j][3];
            SurfaceXYZ[i][j][1]=SurfaceXYZ[i][j][1]/SurfaceXYZ[i][j][3];
            SurfaceXYZ[i][j][2]=SurfaceXYZ[i][j][2]/SurfaceXYZ[i][j][3];
        }
    QuadArray BezierQuadsurfaceface = new QuadArray(n0*n0*4,
            GeometryArray.COORDINATES|GeometryArray.NORMALS);
    int c=0;//以顶点数累加的方式设置数组中顶点的序号
    for(i=0;i<n0;i++)
    {for(j=0;j<n0;j++)
    {//设置一个平面上的4个点
        Point3f A=new Point3f(SurfaceXYZ[i][j][0],SurfaceXYZ[i][j][1],
                SurfaceXYZ[i][j][2]);
        Point3f B=new Point3f(SurfaceXYZ[i][j+1][0],SurfaceXYZ[i][j+1][1],
                SurfaceXYZ[i][j+1][2]);
        Point3f C=new Point3f(SurfaceXYZ[i+1][j+1][0],SurfaceXYZ[i+1][j+1][1],
                SurfaceXYZ[i+1][j+1][2]);
        Point3f D=new Point3f(SurfaceXYZ[i+1][j][0],SurfaceXYZ[i+1][j][1],
                SurfaceXYZ[i+1][j][2]);
//计算4个点形成的平面的法向量
        Vector3f a = new Vector3f(A.x - B.x, A.y - B.y, A.z - B.z);
        Vector3f b = new Vector3f(C.x - B.x, C.y - B.y, C.z - B.z);
        Vector3f n = new Vector3f();
        n.cross(b, a);
        n.normalize();
//设置点的序号
        BezierQuadsurfaceface.setCoordinate(c, A);
        BezierQuadsurfaceface.setCoordinate(c+1, B);
        BezierQuadsurfaceface.setCoordinate(c+2, C);
        BezierQuadsurfaceface.setCoordinate(c+3, D);
//设置点的法向量
        BezierQuadsurfaceface.setNormal(c, n);
        BezierQuadsurfaceface.setNormal(c+1, n);
        BezierQuadsurfaceface.setNormal(c+2, n);
        BezierQuadsurfaceface.setNormal(c+3, n);
        c=c+4;
    }}
    this.addGeometry(BezierQuadsurfaceface);
    this.setAppearance(app);
}}

class  BezierSurfaceControlPoints extends Shape3D
{public BezierSurfaceControlPoints(float[][][] P,Appearance app)
{int i,j,k;
    QuadArray BeziersurfacecontrolPointsNet =new QuadArray(3*3*4,
            GeometryArray.COORDINATES|GeometryArray.NORMALS);
    int c=0;
    for(i=0;i<3;i++)
    {for(j=0;j<3;j++)
    {Point3f A=new Point3f(P[i][j][0],P[i][j][1],P[i][j][2]);
        Point3f B=new Point3f(P[i][j+1][0],P[i][j+1][1],P[i][j+1][2]);
        Point3f C=new Point3f(P[i+1][j+1][0],P[i+1][j+1][1],P[i+1][j+1][2]);
        Point3f D=new Point3f(P[i+1][j][0],P[i+1][j][1],P[i+1][j][2]);
        Vector3f a = new Vector3f(A.x - B.x, A.y - B.y, A.z - B.z);
        Vector3f b = new Vector3f(C.x - B.x, C.y - B.y, C.z - B.z);
        Vector3f n = new Vector3f();
        n.cross(b, a);
        n.normalize();
        BeziersurfacecontrolPointsNet.setCoordinate(c, A);
        BeziersurfacecontrolPointsNet.setCoordinate(c+1, B);
        BeziersurfacecontrolPointsNet.setCoordinate(c+2, C);
        BeziersurfacecontrolPointsNet.setCoordinate(c+3, D);
        BeziersurfacecontrolPointsNet.setNormal(c, n);
        BeziersurfacecontrolPointsNet.setNormal(c+1, n);
        BeziersurfacecontrolPointsNet.setNormal(c+2, n);
        BeziersurfacecontrolPointsNet.setNormal(c+3, n);
        c=c+4;
    }}
    this.addGeometry(BeziersurfacecontrolPointsNet);
    this.setAppearance(app);
}}


class matrixm
{public float CC[][]= new float[4][4];
    int ll,mm,kk;
    public matrixm(int mmm, int kkk, int nnn,float a[][],float b[][])
    {for(ll=0;ll<mmm;ll++)
        for(mm=0;mm<nnn;mm++){CC[ll][mm]=0.f;}
        for(ll=0;ll<mmm;ll++)
            for(mm=0;mm<nnn;mm++)
            {for(kk=0;kk<kkk;kk++) CC[ll][mm]=CC[ll][mm]+a[ll][kk]*b[kk][mm];}
    }}
