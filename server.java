import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
// import java.nio.Buffer;
import java.io.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.w3c.dom.events.MouseEvent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;

class server implements ActionListener {
    JTextField text;
    JTextField key;
    JPanel al;
    static Box vertical = Box.createVerticalBox();
    static JFrame f = new JFrame();
    static DataOutputStream dout;

    public static void operate(int key) //encryption
    {

        JFileChooser fileChooser=new JFileChooser();
        fileChooser.showOpenDialog(null);
        File file=fileChooser.getSelectedFile();
        //file FileInputStream
        try
        {

            FileInputStream fis=new FileInputStream(file);

            byte []data=new byte[fis.available()];
            fis.read(data);
            int i=0;
            for(byte b:data)
            {
                System.out.println(b);
                data[i]=(byte)(b^key);
                i++;
            }

            FileOutputStream fos=new FileOutputStream(file);
            fos.write(data);
            fos.close();
            fis.close();
            JOptionPane.showMessageDialog(null, "Done");

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public server() {

        f.setLayout(null); //top green part
        JPanel pl = new JPanel();
        pl.setBackground(new Color(7, 94, 84));
        pl.setLayout(null);
        pl.setBounds(0, 0, 450, 70);
        f.add(pl);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("pngwing.com (3).png"));
        Image i2 = i1.getImage().getScaledInstance(25, 30, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(0, 20, 35, 30);
        pl.add(back);



        back.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                System.exit(0);
            }
        });

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("pngwing.com (6).png"));
        Image i5 = i4.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6);
        profile.setBounds(40, 10, 50, 50);
        pl.add(profile);



        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("video.png"));
        Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9);
        video.setBounds(300, 20, 30, 30);
        pl.add(video);



        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("phone.png"));
        Image i11 = i10.getImage().getScaledInstance(35, 30, Image.SCALE_DEFAULT);
        ImageIcon i12 = new ImageIcon(i11);
        JLabel caller = new JLabel(i12);
        caller.setBounds(360, 20, 35, 30);
        pl.add(caller);



        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("3icon.png"));
        Image i14 = i13.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i15 = new ImageIcon(i14);
        JLabel options = new JLabel(i15);
        options.setBounds(410, 20, 10, 25);
        pl.add(options);



        JLabel name = new JLabel("Jhon");
        name.setBounds(110, 15, 140, 30);
        name.setForeground(Color.white);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
        pl.add(name);


        JLabel status = new JLabel("online");
        status.setBounds(110, 45, 100, 10);
        status.setForeground(Color.white);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 14));
        pl.add(status);

        al = new JPanel(); //middle grey part 
        al.setBounds(5, 75, 425, 490);
        f.add(al);

        text = new JTextField(); //msg writting part
        text.setBounds(5, 615, 310, 40);
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(text);


        key = new JTextField(); //msg writting part
        key.setBounds(5, 573, 310, 40);
        key.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(key);//key is encrypt ->decrypt code 

        JButton convert = new JButton("Convert");
        convert.setBounds(320, 573, 110, 40);
        convert.setBackground(new Color(7, 94, 84));
        convert.setForeground(Color.white);

        convert.addActionListener(e->{
            System.out.println("button clicked");
            
            String num=key.getText(); //num is the key value in string 
            int temp=Integer.parseInt(num);
    
            operate(temp);
        });
        f.add(convert);
        


        JButton send = new JButton("SEND");
        send.setBounds(320, 615, 110, 40);
        send.setBackground(new Color(7, 94, 84));
        send.setForeground(Color.white);
        send.addActionListener(this);
        // text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(send);



        f.setSize(450, 700);  //whole frame
        f.setLocation(200, 50);
        f.getContentPane().setBackground(Color.WHITE);
        f.setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {

        try {
            String out = text.getText();

            // JLabel output=new JLabel(out );
            JPanel p2 = formatLabel(out);
            // p2.add(output);
            al.setLayout(new BorderLayout());

            JPanel right = new JPanel(new BorderLayout());

            right.add(p2, BorderLayout.LINE_END);

            vertical.add(right);

            vertical.add(Box.createVerticalStrut(15));
            al.add(vertical, BorderLayout.PAGE_START);
            dout.writeUTF(out);

            text.setText("");
            f.repaint();
            f.invalidate();
            f.validate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static JPanel formatLabel(String out) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");

        output.setFont(new Font("Tahoma", Font.PLAIN, 16));

        output.setBackground(new Color(37, 211, 102));
        output.setOpaque(true);

        output.setBorder(new EmptyBorder(15, 15, 15, 50));
        panel.add(output);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        JLabel time = new JLabel();
        time.setText(sdf.format(cal.getTime()));
        panel.add(time);
        return panel;

    }

    public static void main(String args[]) {
        System.out.println("this is server going to start ");

        new server();

        try {
            ServerSocket skt = new ServerSocket(6001);
            while (true) {
                Socket s = skt.accept();
                DataInputStream din = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());

                while (true) {
                    String msg = din.readUTF();
                    JPanel panel = formatLabel(msg);

                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);

                    vertical.add(left);
                    f.validate();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}