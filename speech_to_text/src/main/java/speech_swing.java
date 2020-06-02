import java.awt.*;
import java.awt.event.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.*;
import javax.swing.plaf.*;

import com.jgoodies.forms.factories.*;
import net.miginfocom.swing.*;
//import org.jdesktop.beansbinding.*;
/*
 * Created by JFormDesigner on Mon Jun 01 16:13:54 CST 2020
 */


/**
 * @author 石荣兴
 */
public class speech_swing extends JFrame {
    private OutputStream outputStream;
    private ObjectOutputStream objectOutputStream;
    public String[] info = new String[5];

    public speech_swing() {
        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", 8888);
            outputStream = socket.getOutputStream();
            objectOutputStream = new ObjectOutputStream(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        initComponents();
//        dataOutputStream.writeUTF();
    }

    public String[] submitMouseClicked(MouseEvent e) {
        // TODO add your code here
        String[] list = new String[5];
        list[0] = get_tone();
        list[1] = get_archor();
        list[2] = get_speed();
        list[3] = get_volume();
        list[4] = get_text();
        return list;
    }

    private String get_tone() {
        return tone.getText();
    }

    private String get_archor() {
        return archor.getText();
    }

    private String get_speed() {
        return speed.getText();
    }

    private String get_volume() {
        return volume.getText();
    }

    private String get_text() {
        return input_area.getText();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - 石荣兴
        scrollPane1 = new JScrollPane();
        input_area = new JTextArea();
        panel1 = new JPanel();
        panel2 = new JPanel();
        label1 = new JLabel();
        speed = new JTextField();
        panel3 = new JPanel();
        label2 = new JLabel();
        volume = new JTextField();
        panel4 = new JPanel();
        label3 = new JLabel();
        archor = new JTextField();
        panel5 = new JPanel();
        label4 = new JLabel();
        tone = new JTextField();
        panel6 = new JPanel();
        panel13 = new JPanel();
        panel12 = new JPanel();
        submit = new JButton();

        //======== this ========
        setBackground(new Color(102, 102, 102));
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(3, 3));

        //======== scrollPane1 ========
        {
            scrollPane1.setMaximumSize(new Dimension(2000, 32767));
            scrollPane1.setPreferredSize(new Dimension(205, 87));

            //---- input_area ----
            input_area.setBackground(Color.white);
            input_area.setRows(5);
            input_area.setPreferredSize(new Dimension(103, 85));
            input_area.setToolTipText("\uff08\u6700\u591a100\u5b57\uff09\n");
            input_area.setText("\u8bf7\u8f93\u5165\u60f3\u8981\u8f6c\u8bd1\u7684\u6587\u5b57\uff08\u6700\u591a500\u5b57\uff09");
            scrollPane1.setViewportView(input_area);
        }
        contentPane.add(scrollPane1);

        //======== panel1 ========
        {
            panel1.setBorder(new javax.swing.border.CompoundBorder(new javax.swing.border.TitledBorder(new javax.swing.border
                    .EmptyBorder(0, 0, 0, 0), "JFor\u006dDesi\u0067ner \u0045valu\u0061tion", javax.swing.border.TitledBorder.CENTER, javax
                    .swing.border.TitledBorder.BOTTOM, new java.awt.Font("Dia\u006cog", java.awt.Font.BOLD,
                    12), java.awt.Color.red), panel1.getBorder()));
            panel1.addPropertyChangeListener(new java.beans
                    .PropertyChangeListener() {
                @Override
                public void propertyChange(java.beans.PropertyChangeEvent e) {
                    if ("bord\u0065r".equals(e.
                            getPropertyName())) throw new RuntimeException();
                }
            });
            panel1.setLayout(new GridLayout(2, 2));

            //======== panel2 ========
            {
                panel2.setLayout(new FlowLayout());

                //---- label1 ----
                label1.setText("\u8bed\u901f\uff1a");
                label1.setVerticalAlignment(SwingConstants.TOP);
                panel2.add(label1);

                //---- speed ----
                speed.setBackground(Color.white);
                speed.setColumns(5);
                panel2.add(speed);
            }
            panel1.add(panel2);

            //======== panel3 ========
            {
                panel3.setLayout(new FlowLayout());

                //---- label2 ----
                label2.setText("\u97f3\u91cf\uff1a");
                label2.setVerticalAlignment(SwingConstants.TOP);
                panel3.add(label2);

                //---- volume ----
                volume.setBackground(Color.white);
                volume.setColumns(5);
                panel3.add(volume);
            }
            panel1.add(panel3);

            //======== panel4 ========
            {
                panel4.setLayout(new FlowLayout());

                //---- label3 ----
                label3.setText("\u4e3b\u64ad\u6a21\u5f0f\uff1a");
                label3.setVerticalAlignment(SwingConstants.TOP);
                panel4.add(label3);

                //---- archor ----
                archor.setBackground(Color.white);
                archor.setColumns(5);
                panel4.add(archor);
            }
            panel1.add(panel4);

            //======== panel5 ========
            {
                panel5.setLayout(new FlowLayout());

                //---- label4 ----
                label4.setText("\u97f3\u8c03\uff1a");
                label4.setVerticalAlignment(SwingConstants.TOP);
                panel5.add(label4);

                //---- tone ----
                tone.setBackground(Color.white);
                tone.setColumns(5);
                panel5.add(tone);
            }
            panel1.add(panel5);
        }
        contentPane.add(panel1);

        //======== panel6 ========
        {
            panel6.setLayout(new GridLayout(3, 0));

            //======== panel13 ========
            {
                panel13.setLayout(new FlowLayout());
            }
            panel6.add(panel13);

            //======== panel12 ========
            {
                panel12.setLayout(new FlowLayout());

                //---- submit ----
                submit.setText("\u8f6c\u8bd1");
                submit.setBackground(new Color(238, 238, 238));
                submit.setHorizontalTextPosition(SwingConstants.CENTER);
                submit.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        try {
                            info = submitMouseClicked(e);
                            objectOutputStream.writeObject(info);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                panel12.add(submit);
            }
            panel6.add(panel12);
        }
        contentPane.add(panel6);
        setSize(385, 475);
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - 石荣兴
    private JScrollPane scrollPane1;
    private JTextArea input_area;
    private JPanel panel1;
    private JPanel panel2;
    private JLabel label1;
    private JTextField speed;
    private JPanel panel3;
    private JLabel label2;
    private JTextField volume;
    private JPanel panel4;
    private JLabel label3;
    private JTextField archor;
    private JPanel panel5;
    private JLabel label4;
    private JTextField tone;
    private JPanel panel6;
    private JPanel panel13;
    private JPanel panel12;
    private JButton submit;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
    public static void main(String[] args) {
        speech_swing speech_swing= new speech_swing();
        speech_swing.show();
    }
}
