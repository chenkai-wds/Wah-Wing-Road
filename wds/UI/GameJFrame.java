package com.wds.UI;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
public class GameJFrame extends JFrame implements KeyListener  {
    //创建二维数组，管理数据，图片根据二维数组中的数据进行加载
    int[][] data=new int[4][4];
    //记录空白方块在二维数组中的位置
    int x=0;
    int y=0;
    public GameJFrame(){
        //初始化界面
        initJFrame();
        //初始化菜单
        initJMenuBar();
        //初始化数据（打乱）
        initData();
        //初始化图片
        initImage();
        //让界面显示出来，写在最后
        this.setVisible(true);
    }

    //初始化数据（打乱）
    private void initData() {
        int[] tempArray={0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        Random r=new Random();
        for(int i=0;i<tempArray.length;i++){
            int index=r.nextInt(tempArray.length);
            int temp=tempArray[i];
            tempArray[i]=tempArray[index];
            tempArray[index]=temp;
        }
        for(int i=0;i<tempArray.length;i++){
            if(tempArray[i]==0){
                x=i/4;
                y=i%4;
            }else{
                data[i/4][i%4]=tempArray[i];
            }
        }
    }

    private void initImage() {
        //清空原本已经出现的所有图片
        this.getContentPane().removeAll();
        //创建一个图片ImageIcon的对象
        //ImageIcon icon1=new ImageIcon("D:\\Intellij IDEA\\JavaProject\\JavaSE_Code\\JavaSE_Code\\out\\image\\bird1.jpg");
        //创建一个JLabel的对象（管理容器）
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                //获取加载图片的序号
                int num=data[i][j];
                JLabel jLabel1=new JLabel(new ImageIcon("out\\\\image\\\\bird"+num+".jpg"));
                //指定图片位置
                jLabel1.setBounds(105*j+83,105*i+134,105,105);
                //把管理容器添加到界面中
                //this.add(jLabel);
                //给图片添加边框
                jLabel1.setBorder(new BevelBorder(0));
                //把管理容器添加到界面中
                this.getContentPane().add(jLabel1);
            }
        }
        //刷新一下界面
        this.getContentPane().repaint();
    }

    private void initJMenuBar() {
        //创建整个菜单对象
        JMenuBar jMenuBar =new JMenuBar();
        //创建菜单上面的两个选项的对象 （功能 关于我们）
        JMenu functionJMenu = new JMenu("功能");
        JMenu aboutJMenu = new JMenu("关于我");
        //创建选项下面的条目对象
        JMenuItem replayItem = new JMenuItem("重新游戏");
        JMenuItem reLoginItem = new JMenuItem("重新登录");
        JMenuItem closeItem = new JMenuItem("关闭游戏");
        JMenuItem accountItem = new JMenuItem("关于我");

        //将每一个选项下面的条目添加到选项当中
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        //将菜单里面的两个选项添加到菜单当中
        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        //给整个界面设置菜单
        this.setJMenuBar(jMenuBar);
    }

    private void initJFrame() {
        this.setSize(603,680);
        //设置界面标题
        this.setTitle("拼图单机版 v1.0");
        //设置界面置顶
        this.setAlwaysOnTop(true);
        //设置界面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(3);
        //取消默认的居中放置
        this.setLayout(null);
        //给整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //按下不松时调用这个方法
    @Override
    public void keyPressed(KeyEvent e) {
        int code =e.getKeyCode();
        if(code==65){
            //把界面中所有图片全部删除
            this.getContentPane().removeAll();
            //加载完整图片
            JLabel all=new JLabel(new ImageIcon("out\\image\\bird.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            //刷新界面
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //对上下左右进行判断
        //左：37，上：38，右：39，下：40
        int code =e.getKeyCode();
        if(code==37){
            System.out.println("向左移动");
            if(y==3){
                return;
            }
            //把空白方块右方的数字赋值给空白方块
            data[x][y]=data[x][y+1];
            data[x][y+1]=0;
            y++;
            //调用方法按最新的数字加载图片
            initImage();
        }else if(code==38){
            System.out.println("向上移动");
            if(x==3){
                return;
            }
            //把空白方块下方的数字赋值给空白方块
            data[x][y]=data[x+1][y];
            data[x+1][y]=0;
            x++;
            //调用方法按最新的数字加载图片
            initImage();
        }else if(code==39){
            System.out.println("向右移动");
            if(y==0){
                return;
            }
            //把空白方块左方的数字赋值给空白方块
            data[x][y]=data[x][y-1];
            data[x][y-1]=0;
            y--;
            //调用方法按最新的数字加载图片
            initImage();
        }else if(code==40){
            System.out.println("向下移动");
            if(x==0){
                return;
            }
            //把空白方块上方的数字赋值给空白方块
            data[x][y]=data[x-1][y];
            data[x-1][y]=0;
            x--;
            //调用方法按最新的数字加载图片
            initImage();
        }else if(code==65){
            initImage();
        }
    }
}
