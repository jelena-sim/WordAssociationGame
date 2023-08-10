/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import connectionToDB.DatabaseConnection;
import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import models.Player;
import models.Words;
import models.WordsArray;

public class Game extends javax.swing.JFrame {

    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButtonA1;
    private javax.swing.JButton jButtonA2;
    private javax.swing.JButton jButtonA3;
    private javax.swing.JButton jButtonA4;
    private javax.swing.JButton jButtonB1;
    private javax.swing.JButton jButtonB2;
    private javax.swing.JButton jButtonB3;
    private javax.swing.JButton jButtonB4;
    private javax.swing.JButton jButtonC1;
    private javax.swing.JButton jButtonC2;
    private javax.swing.JButton jButtonC3;
    private javax.swing.JButton jButtonC4;
    private javax.swing.JButton jButtonD1;
    private javax.swing.JButton jButtonD2;
    private javax.swing.JButton jButtonD3;
    private javax.swing.JButton jButtonD4;
    private javax.swing.JButton jButtonEND1;
    private javax.swing.JButton jButtonEND2;
    private javax.swing.JButton jButtonSTART;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1no;
    private javax.swing.JRadioButton jRadioButton1yes;
    private javax.swing.JRadioButton jRadioButton2no;
    private javax.swing.JRadioButton jRadioButton2yes;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea jTextArea;
    private javax.swing.JTextField jTextFieldA;
    private javax.swing.JTextField jTextFieldB;
    private javax.swing.JTextField jTextFieldC;
    private javax.swing.JTextField jTextFieldD;
    private javax.swing.JTextField jTextFieldFINAL;
    private javax.swing.JTextField jTextFieldPlayer1;
    private javax.swing.JTextField jTextFieldPlayer2;
    private javax.swing.JTextField jTextFieldPoints1;
    private javax.swing.JTextField jTextFieldPoints2;

    private boolean start_game = false;
    private Words w = new Words();
    private WordsArray words;
    private String current_player;
    private ArrayList<JButton> buttons = new ArrayList<>();
    private int counter[] = new int[17]; // counter for how many times fields A1,A2,...,D3,D4 were clicked - should be only once (could be a boolean array)
    private int points1 = 0;
    private int points2 = 0;
    private int[] opened = new int[4]; // number of opened fields when the column was solved
    private int solved; // number of solved columns when the final word was solved
    private Player player1, player2;

    public void setButtons() {
        buttons.add(jButtonA1);
        buttons.add(jButtonA2);
        buttons.add(jButtonA3);
        buttons.add(jButtonA4);
        buttons.add(jButtonB1);
        buttons.add(jButtonB2);
        buttons.add(jButtonB3);
        buttons.add(jButtonB4);
        buttons.add(jButtonC1);
        buttons.add(jButtonC2);
        buttons.add(jButtonC3);
        buttons.add(jButtonC4);
        buttons.add(jButtonD1);
        buttons.add(jButtonD2);
        buttons.add(jButtonD3);
        buttons.add(jButtonD4);
    }

    public void setPlayers() {
        player1 = new Player(jTextFieldPlayer1.getText());
        player2 = new Player(jTextFieldPlayer2.getText());
        try {
            String query = "select name, points, games, winrate, wins, losses from players where name=? or name=?";
            PreparedStatement pst = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            pst.setString(1, player1.getName());
            pst.setString(2, player2.getName());
            ResultSet rs = pst.executeQuery();

            for (int i = 0; i < 2; i++) {
                rs.next();
                if (player1.getName().equalsIgnoreCase(rs.getString(1))) {
                    player1.setPoints(rs.getInt(2));
                    player1.setGames(rs.getInt(3));
                    player1.setWinrate(rs.getFloat(4));
                    player1.setWins(rs.getInt(5));
                    player1.setLosses(rs.getInt(6));
                } else {
                    player2.setPoints(rs.getInt(2));
                    player2.setGames(rs.getInt(3));
                    player2.setWinrate(rs.getFloat(4));
                    player2.setWins(rs.getInt(5));
                    player2.setLosses(rs.getInt(6));
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Game() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Asocijacije");
    }

    // initComponents copied from jFramesForm
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jButtonA1 = new javax.swing.JButton();
        jButtonB1 = new javax.swing.JButton();
        jButtonB2 = new javax.swing.JButton();
        jButtonA2 = new javax.swing.JButton();
        jButtonA3 = new javax.swing.JButton();
        jButtonB3 = new javax.swing.JButton();
        jButtonA4 = new javax.swing.JButton();
        jButtonB4 = new javax.swing.JButton();
        jTextFieldA = new javax.swing.JTextField();
        jTextFieldB = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jTextFieldPlayer1 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jTextFieldPlayer2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTextFieldPoints1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldPoints2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButtonEND1 = new javax.swing.JButton();
        jButtonEND2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jRadioButton1yes = new javax.swing.JRadioButton();
        jRadioButton1no = new javax.swing.JRadioButton();
        jRadioButton2yes = new javax.swing.JRadioButton();
        jRadioButton2no = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        jButtonD4 = new javax.swing.JButton();
        jButtonC4 = new javax.swing.JButton();
        jButtonC3 = new javax.swing.JButton();
        jButtonD3 = new javax.swing.JButton();
        jButtonC2 = new javax.swing.JButton();
        jButtonD2 = new javax.swing.JButton();
        jButtonC1 = new javax.swing.JButton();
        jButtonD1 = new javax.swing.JButton();
        jTextFieldC = new javax.swing.JTextField();
        jTextFieldD = new javax.swing.JTextField();
        jButtonSTART = new javax.swing.JButton();
        jTextFieldFINAL = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(640, 260));

        jButtonA1.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jButtonA1.setText("A1");
        jButtonA1.setPreferredSize(new java.awt.Dimension(200, 40));
        jButtonA1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonA1ActionPerformed(evt);
            }
        });

        jButtonB1.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jButtonB1.setText("B1");
        jButtonB1.setPreferredSize(new java.awt.Dimension(200, 40));
        jButtonB1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonB1ActionPerformed(evt);
            }
        });

        jButtonB2.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jButtonB2.setText("B2");
        jButtonB2.setPreferredSize(new java.awt.Dimension(200, 40));
        jButtonB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonB2ActionPerformed(evt);
            }
        });

        jButtonA2.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jButtonA2.setText("A2");
        jButtonA2.setPreferredSize(new java.awt.Dimension(200, 40));
        jButtonA2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonA2ActionPerformed(evt);
            }
        });

        jButtonA3.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jButtonA3.setText("A3");
        jButtonA3.setPreferredSize(new java.awt.Dimension(200, 40));
        jButtonA3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonA3ActionPerformed(evt);
            }
        });

        jButtonB3.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jButtonB3.setText("B3");
        jButtonB3.setPreferredSize(new java.awt.Dimension(200, 40));
        jButtonB3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonB3ActionPerformed(evt);
            }
        });

        jButtonA4.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jButtonA4.setText("A4");
        jButtonA4.setPreferredSize(new java.awt.Dimension(200, 40));
        jButtonA4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonA4ActionPerformed(evt);
            }
        });

        jButtonB4.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jButtonB4.setText("B4");
        jButtonB4.setPreferredSize(new java.awt.Dimension(200, 40));
        jButtonB4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonB4ActionPerformed(evt);
            }
        });

        jTextFieldA.setEditable(false);
        jTextFieldA.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jTextFieldA.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldA.setText("A");
        jTextFieldA.setPreferredSize(new java.awt.Dimension(200, 40));
        jTextFieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAActionPerformed(evt);
            }
        });

        jTextFieldB.setEditable(false);
        jTextFieldB.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jTextFieldB.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldB.setText("B");
        jTextFieldB.setPreferredSize(new java.awt.Dimension(200, 40));
        jTextFieldB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jButtonA2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(200, 200, 200)
                                                        .addComponent(jButtonB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                        .addComponent(jButtonA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(200, 200, 200)
                                                        .addComponent(jButtonB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jButtonA3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(200, 200, 200)
                                                .addComponent(jButtonB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jButtonA4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextFieldA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(200, 200, 200)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jTextFieldB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jButtonB4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonA1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonB1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonA2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonB2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonA3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonB3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonA4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonB4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12))
        );

        jPanel2.setPreferredSize(new java.awt.Dimension(640, 720));

        jTextFieldPlayer1.setEditable(false);
        jTextFieldPlayer1.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        jTextFieldPlayer1.setForeground(new java.awt.Color(255, 102, 102));
        jTextFieldPlayer1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPlayer1.setText("IGRAČ 1");
        jTextFieldPlayer1.setPreferredSize(new java.awt.Dimension(220, 32));
        jTextFieldPlayer1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //jTextFieldPlayer1ActionPerformed(evt);
            }
        });

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setPreferredSize(new java.awt.Dimension(720, 10));

        jTextFieldPlayer2.setEditable(false);
        jTextFieldPlayer2.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        jTextFieldPlayer2.setForeground(new java.awt.Color(0, 102, 204));
        jTextFieldPlayer2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPlayer2.setText("IGRAČ 2");
        jTextFieldPlayer2.setPreferredSize(new java.awt.Dimension(220, 32));
        jTextFieldPlayer2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //jTextFieldPlayer2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("IME");
        jLabel1.setPreferredSize(new java.awt.Dimension(90, 30));

        jTextFieldPoints1.setEditable(false);
        jTextFieldPoints1.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        jTextFieldPoints1.setForeground(new java.awt.Color(255, 102, 102));
        jTextFieldPoints1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPoints1.setPreferredSize(new java.awt.Dimension(220, 32));

        jLabel2.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("POENI");
        jLabel2.setPreferredSize(new java.awt.Dimension(90, 30));

        jTextFieldPoints2.setEditable(false);
        jTextFieldPoints2.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        jTextFieldPoints2.setForeground(new java.awt.Color(0, 102, 204));
        jTextFieldPoints2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldPoints2.setPreferredSize(new java.awt.Dimension(220, 32));

        jLabel3.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("POTEZ");
        jLabel3.setPreferredSize(new java.awt.Dimension(90, 30));

        jButtonEND1.setBackground(new java.awt.Color(255, 102, 102));
        jButtonEND1.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jButtonEND1.setText("KRAJ");
        jButtonEND1.setPreferredSize(new java.awt.Dimension(220, 32));
        jButtonEND1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEND1ActionPerformed(evt);
            }
        });

        jButtonEND2.setBackground(new java.awt.Color(0, 102, 204));
        jButtonEND2.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jButtonEND2.setText("KRAJ");
        jButtonEND2.setPreferredSize(new java.awt.Dimension(220, 32));
        jButtonEND2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEND2ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel4.setText("ISTORIJA POTEZA:");
        jLabel4.setPreferredSize(new java.awt.Dimension(300, 30));

        jTextArea.setEditable(false);
        jTextArea.setColumns(25);
        jTextArea.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        jTextArea.setRows(5);
        jScrollPane1.setViewportView(jTextArea);

        jScrollPane2.setViewportView(jScrollPane1);

        jLabel6.setFont(new java.awt.Font("DialogInput", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("IGRAČ");
        jLabel6.setPreferredSize(new java.awt.Dimension(90, 30));

        jComboBox1.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        jComboBox1.setPreferredSize(new java.awt.Dimension(220, 32));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        jComboBox2.setFont(new java.awt.Font("DialogInput", 0, 18)); // NOI18N
        jComboBox2.setPreferredSize(new java.awt.Dimension(220, 32));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox4ActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("DialogInput", 2, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 102, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("NOVI IGRAČ?");
        jLabel7.setPreferredSize(new java.awt.Dimension(180, 30));

        jSeparator2.setPreferredSize(new java.awt.Dimension(400, 10));

        buttonGroup1.add(jRadioButton1yes);
        jRadioButton1yes.setFont(new java.awt.Font("DialogInput", 2, 18)); // NOI18N
        jRadioButton1yes.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton1yes.setText("DA");
        jRadioButton1yes.setPreferredSize(new java.awt.Dimension(60, 30));
        jRadioButton1yes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1yesActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton1no);
        jRadioButton1no.setFont(new java.awt.Font("DialogInput", 2, 18)); // NOI18N
        jRadioButton1no.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton1no.setText("NE");
        jRadioButton1no.setPreferredSize(new java.awt.Dimension(60, 30));
        jRadioButton1no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1noActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton2yes);
        jRadioButton2yes.setFont(new java.awt.Font("DialogInput", 2, 18)); // NOI18N
        jRadioButton2yes.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton2yes.setText("DA");
        jRadioButton2yes.setPreferredSize(new java.awt.Dimension(60, 30));
        jRadioButton2yes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2yesActionPerformed(evt);
            }
        });

        buttonGroup2.add(jRadioButton2no);
        jRadioButton2no.setFont(new java.awt.Font("DialogInput", 2, 18)); // NOI18N
        jRadioButton2no.setForeground(new java.awt.Color(102, 102, 102));
        jRadioButton2no.setText("NE");
        jRadioButton2no.setPreferredSize(new java.awt.Dimension(60, 30));
        jRadioButton2no.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2noActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(265, 265, 265)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(25, 25, 25)
                                                .addComponent(jButtonEND2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 582, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jButtonEND1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(jTextFieldPoints1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(25, 25, 25)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(25, 25, 25)
                                                .addComponent(jTextFieldPoints2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jTextFieldPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(25, 25, 25)
                                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addGap(25, 25, 25)
                                                                                .addComponent(jTextFieldPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(60, 60, 60)
                                        .addComponent(jRadioButton1yes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButton1no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButton2yes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jRadioButton2no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(804, 804, 804))
        );
        jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jRadioButton1yes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jRadioButton1no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jRadioButton2yes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jRadioButton2no, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jTextFieldPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextFieldPlayer2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jTextFieldPoints1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jTextFieldPoints2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButtonEND1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jButtonEND2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel3.setPreferredSize(new java.awt.Dimension(640, 260));

        jButtonD4.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jButtonD4.setText("D4");
        jButtonD4.setPreferredSize(new java.awt.Dimension(200, 40));
        jButtonD4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonD4ActionPerformed(evt);
            }
        });

        jButtonC4.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jButtonC4.setText("C4");
        jButtonC4.setPreferredSize(new java.awt.Dimension(200, 40));
        jButtonC4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonC4ActionPerformed(evt);
            }
        });

        jButtonC3.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jButtonC3.setText("C3");
        jButtonC3.setPreferredSize(new java.awt.Dimension(200, 40));
        jButtonC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonC3ActionPerformed(evt);
            }
        });

        jButtonD3.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jButtonD3.setText("D3");
        jButtonD3.setPreferredSize(new java.awt.Dimension(200, 40));
        jButtonD3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonD3ActionPerformed(evt);
            }
        });

        jButtonC2.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jButtonC2.setText("C2");
        jButtonC2.setPreferredSize(new java.awt.Dimension(200, 40));
        jButtonC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonC2ActionPerformed(evt);
            }
        });

        jButtonD2.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jButtonD2.setText("D2");
        jButtonD2.setPreferredSize(new java.awt.Dimension(200, 40));
        jButtonD2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonD2ActionPerformed(evt);
            }
        });

        jButtonC1.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jButtonC1.setText("C1");
        jButtonC1.setPreferredSize(new java.awt.Dimension(200, 40));
        jButtonC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonC1ActionPerformed(evt);
            }
        });

        jButtonD1.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jButtonD1.setText("D1");
        jButtonD1.setPreferredSize(new java.awt.Dimension(200, 40));
        jButtonD1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonD1ActionPerformed(evt);
            }
        });

        jTextFieldC.setEditable(false);
        jTextFieldC.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jTextFieldC.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldC.setText("C");
        jTextFieldC.setPreferredSize(new java.awt.Dimension(200, 40));
        jTextFieldC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCActionPerformed(evt);
            }
        });

        jTextFieldD.setEditable(false);
        jTextFieldD.setFont(new java.awt.Font("DialogInput", 1, 24)); // NOI18N
        jTextFieldD.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldD.setText("D");
        jTextFieldD.setPreferredSize(new java.awt.Dimension(200, 40));
        jTextFieldD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jButtonC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(200, 200, 200)
                                                .addComponent(jButtonD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jButtonC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(200, 200, 200)
                                                .addComponent(jButtonD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jButtonC4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(200, 200, 200)
                                                .addComponent(jButtonD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jTextFieldC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(200, 200, 200)
                                                .addComponent(jTextFieldD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jButtonC3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(200, 200, 200)
                                                .addComponent(jButtonD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(10, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jTextFieldC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jTextFieldD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonC4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonD4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButtonC3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonD3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButtonD2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonC2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jButtonD1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jButtonC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12))
        );

        jButtonSTART.setBackground(new java.awt.Color(0, 153, 102));
        jButtonSTART.setFont(new java.awt.Font("DialogInput", 1, 48)); // NOI18N
        jButtonSTART.setText("POČETAK IGRE");
        jButtonSTART.setPreferredSize(new java.awt.Dimension(600, 70));
        jButtonSTART.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSTARTActionPerformed(evt);
            }
        });

        jTextFieldFINAL.setEditable(false);
        jTextFieldFINAL.setFont(new java.awt.Font("DialogInput", 1, 36)); // NOI18N
        jTextFieldFINAL.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldFINAL.setText("REŠENjE");
        jTextFieldFINAL.setPreferredSize(new java.awt.Dimension(250, 55));
        jTextFieldFINAL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldFINALActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(jButtonSTART, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(205, 205, 205)
                                                .addComponent(jTextFieldFINAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(646, 646, 646))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jTextFieldFINAL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jButtonSTART, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }

    private void jButtonSTARTActionPerformed(java.awt.event.ActionEvent evt) {

        if ((jRadioButton1yes.isSelected() || jRadioButton1no.isSelected()) && (jRadioButton2yes.isSelected() || jRadioButton2no.isSelected())
                && !(jTextFieldPlayer1.getText().isBlank()) && !(jTextFieldPlayer2.getText().isBlank()) 
                && !(jTextFieldPlayer1.getText().equalsIgnoreCase(jTextFieldPlayer2.getText()))) {

            if (checkName1() && checkName2()) {
                counter[16]++;

                if (counter[16] == 1) {

                    if (jRadioButton1yes.isSelected()) {
                        try {
                            String query = "insert into players (name,points,games,winrate,wins,losses) values (?,0,0,0,0,0)";
                            PreparedStatement pst = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
                            pst.setString(1, jTextFieldPlayer1.getText());
                            pst.executeUpdate();
                            pst.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    if (jRadioButton2yes.isSelected()) {
                        try {
                            String query = "insert into players (name,points,games,winrate,wins,losses) values (?,0,0,0,0,0)";
                            PreparedStatement pst = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
                            pst.setString(1, jTextFieldPlayer2.getText());
                            pst.executeUpdate();
                            pst.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    try {
                        Statement st = DatabaseConnection.getInstance().getConnection().createStatement();
                        ResultSet rs = st.executeQuery("select count(*) from words_table");
                        int n = 0;
                        while (rs.next()) {
                            n = rs.getInt(1);
                        }
                        int random = (int) (Math.random() * n) + 1;

                        String query = "SELECT a1, a2, a3, a4, a, b1, b2, b3, b4, b, c1, c2, c3, c4, c, d1, d2, d3, d4, d, final_word FROM words_table WHERE id=?";
                        PreparedStatement pst = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
                        pst.setInt(1, random);
                        rs = pst.executeQuery();

                        while (rs.next()) {
                            w.setA1(rs.getString(1));
                            w.setA2(rs.getString(2));
                            w.setA3(rs.getString(3));
                            w.setA4(rs.getString(4));
                            w.setA(rs.getString(5));
                            w.setB1(rs.getString(6));
                            w.setB2(rs.getString(7));
                            w.setB3(rs.getString(8));
                            w.setB4(rs.getString(9));
                            w.setB(rs.getString(10));
                            w.setC1(rs.getString(11));
                            w.setC2(rs.getString(12));
                            w.setC3(rs.getString(13));
                            w.setC4(rs.getString(14));
                            w.setC(rs.getString(15));
                            w.setD1(rs.getString(16));
                            w.setD2(rs.getString(17));
                            w.setD3(rs.getString(18));
                            w.setD4(rs.getString(19));
                            w.setD(rs.getString(20));
                            w.setF(rs.getString(21));
                        }

                        start_game = true;

                        jRadioButton1yes.setEnabled(false);
                        jRadioButton1no.setEnabled(false);
                        jRadioButton2yes.setEnabled(false);
                        jRadioButton2no.setEnabled(false);
                        jComboBox1.setEnabled(false);
                        jComboBox2.setEnabled(false);

                        jTextFieldPoints1.setText(Integer.toString(points1));
                        jTextFieldPoints2.setText(Integer.toString(points2));
                        jTextFieldPlayer1.setEditable(false);
                        jTextFieldPlayer2.setEditable(false);
                        jTextFieldA.setEditable(true);
                        jTextFieldB.setEditable(true);
                        jTextFieldC.setEditable(true);
                        jTextFieldD.setEditable(true);
                        jTextFieldFINAL.setEditable(true);

                        words = new WordsArray(w);
                        current_player = jTextFieldPlayer1.getText();
                        setButtons();
                        setPlayers();
                        turn(current_player);

                    } catch (SQLException ex) {
                        Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    Object[] options = {"Da", "Ne"};
                    int result = JOptionPane.showOptionDialog(null, "Da li želite da prekinete trenutnu igru i započnete novu?", "Nova partija",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
                    if (result == JOptionPane.YES_OPTION) {
                        this.dispose();
                        new Game().setVisible(true);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Izaberite različite igrače!");
        }
    }

    private void jTextFieldFINALActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            jTextArea.append(current_player + " pogađa konačno rešenje: " + jTextFieldFINAL.getText());
            if (jTextFieldFINAL.getText().equalsIgnoreCase(w.getF())) {
                jTextArea.append(" - tačno!\n");
                jTextFieldFINAL.setText(w.getF());
                jTextFieldFINAL.setBackground(Color.GRAY);
                jTextFieldFINAL.setEditable(false);
                jTextFieldA.setText(w.getA());
                jTextFieldA.setBackground(Color.GRAY);
                jTextFieldA.setEditable(false);
                jTextFieldB.setText(w.getB());
                jTextFieldB.setBackground(Color.GRAY);
                jTextFieldB.setEditable(false);
                jTextFieldC.setText(w.getC());
                jTextFieldC.setBackground(Color.GRAY);
                jTextFieldC.setEditable(false);
                jTextFieldD.setText(w.getD());
                jTextFieldD.setBackground(Color.GRAY);
                jTextFieldD.setEditable(false);

                disableAllButtons();

                points_final(solved);
                jTextArea.append("* * * * * * * * * * * * * * * * * * * * * * * * *\n");
                if (points1 > points2) {
                    jTextArea.append("POBEDNIK JE: " + jTextFieldPlayer1.getText() + "!!!\n");
                } else if (points1 < points2) {
                    jTextArea.append("POBEDNIK JE: " + jTextFieldPlayer2.getText() + "!!!\n");
                } else {
                    jTextArea.append("NEREŠENO!!!\n");
                }
                jTextArea.append("\n");

                updatePlayers();
                updatePlayersTable(player1);
                updatePlayersTable(player2);
                PlayersTable list = new PlayersTable();
                list.setVisible(true);
            } else {
                jTextFieldFINAL.setText("REŠENjE");
                jTextArea.append(" - netačno!\n");
                for (int i = 0; i < buttons.size(); i++) {
                    if (!buttons.get(i).isEnabled()) {
                        buttons.get(i).setEnabled(true);
                    }
                }
                switch_player();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
            jTextFieldFINAL.setText("REŠENjE");
        }

    }

    private void jButtonA1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            counter[0]++;
            if (counter[0] == 1) {
                jTextArea.append(current_player + " otvara polje A1.\n");
                jButtonA1.setText(w.getA1());
                jButtonA1.setContentAreaFilled(false);
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setEnabled(false);
                }
                opened[0]++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
        }
    }

    private void jButtonA2ActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            counter[1]++;
            if (counter[1] == 1) {
                jTextArea.append(current_player + " otvara polje A2.\n");
                jButtonA2.setText(w.getA2());
                jButtonA2.setContentAreaFilled(false);
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setEnabled(false);
                }
                opened[0]++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
        }
    }

    private void jButtonA3ActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            counter[2]++;
            if (counter[2] == 1) {
                jTextArea.append(current_player + " otvara polje A3.\n");
                jButtonA3.setText(w.getA3());
                jButtonA3.setContentAreaFilled(false);
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setEnabled(false);
                }
                opened[0]++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
        }
    }

    private void jButtonA4ActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            counter[3]++;
            if (counter[3] == 1) {
                jTextArea.append(current_player + " otvara polje A4.\n");
                jButtonA4.setText(w.getA4());
                jButtonA4.setContentAreaFilled(false);
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setEnabled(false);
                }
                opened[0]++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
        }
    }

    private void jButtonB1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            counter[4]++;
            if (counter[4] == 1) {
                jTextArea.append(current_player + " otvara polje B1.\n");
                jButtonB1.setText(w.getB1());
                jButtonB1.setContentAreaFilled(false);
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setEnabled(false);
                }
                opened[1]++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
        }
    }

    private void jButtonB2ActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            counter[5]++;
            if (counter[5] == 1) {
                jTextArea.append(current_player + " otvara polje B2.\n");
                jButtonB2.setText(w.getB2());
                jButtonB2.setContentAreaFilled(false);
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setEnabled(false);
                }
                opened[1]++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
        }
    }

    private void jButtonB3ActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            counter[6]++;
            if (counter[6] == 1) {
                jTextArea.append(current_player + " otvara polje B3.\n");
                jButtonB3.setText(w.getB3());
                jButtonB3.setContentAreaFilled(false);
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setEnabled(false);
                }
                opened[1]++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
        }
    }

    private void jButtonB4ActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            counter[7]++;
            if (counter[7] == 1) {
                jTextArea.append(current_player + " otvara polje B4.\n");
                jButtonB4.setText(w.getB4());
                jButtonB4.setContentAreaFilled(false);
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setEnabled(false);
                }
                opened[1]++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
        }
    }

    private void jButtonC1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            counter[8]++;
            if (counter[8] == 1) {
                jTextArea.append(current_player + " otvara polje C1.\n");
                jButtonC1.setText(w.getC1());
                jButtonC1.setContentAreaFilled(false);
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setEnabled(false);
                }
                opened[2]++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
        }
    }

    private void jButtonC2ActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            counter[9]++;
            if (counter[9] == 1) {
                jTextArea.append(current_player + " otvara polje C2.\n");
                jButtonC2.setText(w.getC2());
                jButtonC2.setContentAreaFilled(false);
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setEnabled(false);
                }
                opened[2]++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
        }
    }

    private void jButtonC3ActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            counter[10]++;
            if (counter[10] == 1) {
                jTextArea.append(current_player + " otvara polje C3.\n");
                jButtonC3.setText(w.getC3());
                jButtonC3.setContentAreaFilled(false);
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setEnabled(false);
                }
                opened[2]++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
        }
    }

    private void jButtonC4ActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            counter[11]++;
            if (counter[11] == 1) {
                jTextArea.append(current_player + " otvara polje C4.\n");
                jButtonC4.setText(w.getC4());
                jButtonC4.setContentAreaFilled(false);
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setEnabled(false);
                }
                opened[2]++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
        }
    }

    private void jButtonD1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            counter[12]++;
            if (counter[12] == 1) {
                jTextArea.append(current_player + " otvara polje D1.\n");
                jButtonD1.setText(w.getD1());
                jButtonD1.setContentAreaFilled(false);
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setEnabled(false);
                }
                opened[3]++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
        }
    }

    private void jButtonD2ActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            counter[13]++;
            if (counter[13] == 1) {
                jTextArea.append(current_player + " otvara polje D2.\n");
                jButtonD2.setText(w.getD2());
                jButtonD2.setContentAreaFilled(false);
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setEnabled(false);
                }
                opened[3]++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
        }
    }

    private void jButtonD3ActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            counter[14]++;
            if (counter[14] == 1) {
                jTextArea.append(current_player + " otvara polje D3.\n");
                jButtonD3.setText(w.getD3());
                jButtonD3.setContentAreaFilled(false);
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setEnabled(false);
                }
                opened[3]++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
        }
    }

    private void jButtonD4ActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            counter[15]++;
            if (counter[15] == 1) {
                jTextArea.append(current_player + " otvara polje D4.\n");
                jButtonD4.setText(w.getD4());
                jButtonD4.setContentAreaFilled(false);
                for (int i = 0; i < buttons.size(); i++) {
                    buttons.get(i).setEnabled(false);
                }
                opened[3]++;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
        }
    }

    private void jTextFieldAActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            if (opened[0] == 0) {
                jTextArea.append("Akcija nije moguća: kolona nema otvorenih polja.\n");
                jTextFieldA.setText("A");
            } else {
                jTextArea.append(current_player + " pogađa polje A: " + jTextFieldA.getText());
                if (jTextFieldA.getText().equalsIgnoreCase(w.getA())) {
                    jTextArea.append(" - tačno!\n");
                    points_column(opened[0]);
                    solved++;
                    jTextFieldA.setText(w.getA());
                    jTextFieldA.setBackground(Color.GRAY);
                    jTextFieldA.setEditable(false);

                    for (int i = 0; i < 4; i++) {
                        buttons.get(i).setEnabled(true);
                    }

                    for (int i = 0; i < 4; i++) {
                        if (buttons.get(i).isEnabled()) {
                            buttons.get(i).setText(words.getWords()[i]);
                            buttons.get(i).setContentAreaFilled(false);
                            buttons.get(i).setEnabled(false);
                            counter[i]++;
                        }
                    }

                } else {
                    jTextFieldA.setText("A");
                    jTextArea.append(" - netačno!\n");
                    for (int i = 0; i < buttons.size(); i++) {
                        if (!buttons.get(i).isEnabled()) {
                            buttons.get(i).setEnabled(true);
                        }
                    }
                    switch_player();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
            jTextFieldA.setText("A");
        }
    }

    private void jTextFieldBActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            if (opened[1] == 0) {
                jTextArea.append("Akcija nije moguća: kolona nema otvorenih polja.\n");
                jTextFieldB.setText("B");
            } else {
                jTextArea.append(current_player + " pogađa polje B: " + jTextFieldB.getText());
                if (jTextFieldB.getText().equalsIgnoreCase(w.getB())) {
                    jTextArea.append(" - tačno!\n");
                    points_column(opened[1]);
                    solved++;
                    jTextFieldB.setText(w.getB());
                    jTextFieldB.setBackground(Color.GRAY);
                    jTextFieldB.setEditable(false);

                    for (int i = 4; i < 8; i++) {
                        buttons.get(i).setEnabled(true);
                    }

                    for (int i = 4; i < 8; i++) {
                        if (buttons.get(i).isEnabled()) {
                            buttons.get(i).setText(words.getWords()[i + 1]);
                            buttons.get(i).setContentAreaFilled(false);
                            buttons.get(i).setEnabled(false);
                            counter[i]++;
                        }
                    }
                } else {
                    jTextFieldB.setText("B");
                    jTextArea.append(" - netačno!\n");
                    for (int i = 0; i < buttons.size(); i++) {
                        if (!buttons.get(i).isEnabled()) {
                            buttons.get(i).setEnabled(true);
                        }
                    }
                    switch_player();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
            jTextFieldB.setText("B");
        }
    }

    private void jTextFieldCActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            if (opened[2] == 0) {
                jTextArea.append("Akcija nije moguća: kolona nema otvorenih polja.\n");
                jTextFieldC.setText("C");
            } else {
                jTextArea.append(current_player + " pogađa polje C: " + jTextFieldC.getText());
                if (jTextFieldC.getText().equalsIgnoreCase(w.getC())) {
                    jTextArea.append(" - tačno!\n");
                    points_column(opened[2]);
                    solved++;
                    jTextFieldC.setText(w.getC());
                    jTextFieldC.setBackground(Color.GRAY);
                    jTextFieldC.setEditable(false);

                    for (int i = 8; i < 12; i++) {
                        buttons.get(i).setEnabled(true);
                    }

                    for (int i = 8; i < 12; i++) {
                        if (buttons.get(i).isEnabled()) {
                            buttons.get(i).setText(words.getWords()[i + 2]);
                            buttons.get(i).setContentAreaFilled(false);
                            buttons.get(i).setEnabled(false);
                            counter[i]++;
                        }
                    }
                } else {
                    jTextFieldC.setText("C");
                    jTextArea.append(" - netačno!\n");
                    for (int i = 0; i < buttons.size(); i++) {
                        if (!buttons.get(i).isEnabled()) {
                            buttons.get(i).setEnabled(true);
                        }
                    }
                    switch_player();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
            jTextFieldC.setText("C");
        }
    }

    private void jTextFieldDActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            if (opened[3] == 0) {
                jTextArea.append("Akcija nije moguća: kolona nema otvorenih polja.\n");
                jTextFieldD.setText("D");
            } else {
                jTextArea.append(current_player + " pogađa polje D: " + jTextFieldD.getText());
                if (jTextFieldD.getText().equalsIgnoreCase(w.getD())) {
                    jTextArea.append(" - tačno!\n");
                    points_column(opened[3]);
                    solved++;
                    jTextFieldD.setText(w.getD());
                    jTextFieldD.setBackground(Color.GRAY);
                    jTextFieldD.setEditable(false);

                    for (int i = 12; i < 16; i++) {
                        buttons.get(i).setEnabled(true);
                    }

                    for (int i = 12; i < 16; i++) {
                        if (buttons.get(i).isEnabled()) {
                            buttons.get(i).setText(words.getWords()[i + 3]);
                            buttons.get(i).setContentAreaFilled(false);
                            buttons.get(i).setEnabled(false);
                            counter[i]++;
                        }
                    }
                } else {
                    jTextFieldD.setText("D");
                    jTextArea.append(" - netačno!\n");
                    for (int i = 0; i < buttons.size(); i++) {
                        if (!buttons.get(i).isEnabled()) {
                            buttons.get(i).setEnabled(true);
                        }
                    }
                    switch_player();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
            jTextFieldC.setText("D");
        }
    }

    private void jButtonEND1ActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            for (int i = 0; i < buttons.size(); i++) {
                if (!buttons.get(i).isEnabled()) {
                    buttons.get(i).setEnabled(true);
                }
            }
            current_player = jTextFieldPlayer2.getText();
            jButtonEND2.setEnabled(true);
            turn(current_player);
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
        }
    }

    private void jButtonEND2ActionPerformed(java.awt.event.ActionEvent evt) {
        if (start_game) {
            for (int i = 0; i < buttons.size(); i++) {
                if (!buttons.get(i).isEnabled()) {
                    buttons.get(i).setEnabled(true);
                }
            }
            current_player = jTextFieldPlayer1.getText();
            jButtonEND1.setEnabled(true);
            turn(current_player);
        } else {
            JOptionPane.showMessageDialog(null, "Pritisnite zeleno dugme za početak igre!");
        }
    }

    private void turn(String player) {
        if (!jTextFieldFINAL.getText().equalsIgnoreCase(w.getF())) {
            jTextArea.append(player + " je na potezu.\n");
            if (current_player.equals(jTextFieldPlayer1.getText())) {
                jButtonEND2.setEnabled(false);
            } else {
                jButtonEND1.setEnabled(false);
            }
        }
    }

    private void switch_player() {
        if (current_player.equals(jTextFieldPlayer1.getText())) {
            current_player = jTextFieldPlayer2.getText();
            jButtonEND2.setEnabled(true);
            turn(current_player);
        } else {
            current_player = jTextFieldPlayer1.getText();
            jButtonEND1.setEnabled(true);
            turn(current_player);
        }
    }

    private void disableAllButtons() {
        for (int i = 0; i < 16; i++) {
            buttons.get(i).setEnabled(true);
        }
        for (int i = 0; i < 4; i++) {
            if (buttons.get(i).isEnabled()) {
                buttons.get(i).setText(words.getWords()[i]);
                buttons.get(i).setContentAreaFilled(false);
                buttons.get(i).setEnabled(false);
            }
        }
        for (int i = 4; i < 8; i++) {
            if (buttons.get(i).isEnabled()) {
                buttons.get(i).setText(words.getWords()[i + 1]);
                buttons.get(i).setContentAreaFilled(false);
                buttons.get(i).setEnabled(false);
            }
        }
        for (int i = 8; i < 12; i++) {
            if (buttons.get(i).isEnabled()) {
                buttons.get(i).setText(words.getWords()[i + 2]);
                buttons.get(i).setContentAreaFilled(false);
                buttons.get(i).setEnabled(false);
            }
        }
        for (int i = 12; i < 16; i++) {
            if (buttons.get(i).isEnabled()) {
                buttons.get(i).setText(words.getWords()[i + 3]);
                buttons.get(i).setContentAreaFilled(false);
                buttons.get(i).setEnabled(false);
            }
        }
        jButtonEND1.setEnabled(false);
        jButtonEND2.setEnabled(false);
    }

    private void points_column(int b) { // b will be opened[i]
        int p; // points for current column
        switch (b) {
            case 1:
                p = 7;
                break;
            case 2:
                p = 5;
                break;
            case 3:
                p = 3;
                break;
            case 4:
                p = 1;
                break;
            default:
                p = 0;
        }
        if (p == 1) {
            jTextArea.append(current_player + " dobija " + Integer.toString(p) + " poen.\n");
        } else {
            jTextArea.append(current_player + " dobija " + Integer.toString(p) + " poena.\n");
        }
        if (current_player.equals(jTextFieldPlayer1.getText())) {
            points1 += p;
            jTextFieldPoints1.setText(Integer.toString(points1));
        } else {
            points2 += p;
            jTextFieldPoints2.setText(Integer.toString(points2));
        }
    }

    private void points_final(int b) {
        int p; 
        switch (b) {
            case 0:
                p = 20;
                break;
            case 1:
                p = 15;
                break;
            case 2:
                p = 13;
                break;
            case 3:
                p = 11;
                break;
            case 4:
                p = 9;
                break;
            default:
                p = 0;
        }
        jTextArea.append(current_player + " dobija " + Integer.toString(p) + " poena.\n");
        if (current_player.equals(jTextFieldPlayer1.getText())) {
            points1 += p;
            jTextFieldPoints1.setText(Integer.toString(points1));
        } else {
            points2 += p;
            jTextFieldPoints2.setText(Integer.toString(points2));
        }
    }

    private void initComboBox1() {
        jComboBox1.removeAllItems();
        try {
            Statement st = DatabaseConnection.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery("select name from players");
            while (rs.next()) {
                jComboBox1.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initComboBox2() {
        jComboBox2.removeAllItems();
        try {
            Statement st = DatabaseConnection.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery("select name from players");
            while (rs.next()) {
                jComboBox2.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean checkname(String name) {
        try {
            Statement st = DatabaseConnection.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery("select name from players");
            while (rs.next()) {
                if (name.equalsIgnoreCase(rs.getString(1))) {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    private boolean checkName1() {
        if (counter[16] == 0) {
            if (jRadioButton1yes.isSelected()) {
                if (!checkname(jTextFieldPlayer1.getText())) {
                    JOptionPane.showMessageDialog(null, "Ime prvog igrača već postoji!");
                    return false;
                }
            }
        }
        return true;
    }

    private boolean checkName2() {
        if (counter[16] == 0) {
            if (jRadioButton2yes.isSelected()) {
                if (!checkname(jTextFieldPlayer2.getText())) {
                    JOptionPane.showMessageDialog(null, "Ime drugog igrača već postoji!");
                    return false;
                }
            }
        }
        return true;
    }

    private void updatePlayersTable(Player p) {
        try {
            String query = "update players set points=?, games=?, winrate=?, wins=?, losses=? where name=?";
            PreparedStatement pst = DatabaseConnection.getInstance().getConnection().prepareStatement(query);
            pst.setInt(1, p.getPoints());
            pst.setInt(2, p.getGames());
            pst.setFloat(3, p.getWinrate());
            pst.setInt(4, p.getWins());
            pst.setInt(5, p.getLosses());
            pst.setString(6, p.getName());
            pst.executeUpdate();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void updatePlayers() {
        player1.setPoints(player1.getPoints() + points1);
        player1.setGames(player1.getGames() + 1);
        player2.setPoints(player2.getPoints() + points2);
        player2.setGames(player2.getGames() + 1);
        if (points1 > points2) {
            player1.setWins(player1.getWins() + 1);
            player2.setLosses(player2.getLosses() + 1);
        } else {
            player2.setWins(player2.getWins() + 1);
            player1.setLosses(player1.getLosses() + 1);
        }
        player1.setWinrate(((float) player1.getWins() / (player1.getWins() + player1.getLosses())) * 100);
        player2.setWinrate(((float) player2.getWins() / (player2.getWins() + player2.getLosses())) * 100);
    }

    private void jRadioButton1yesActionPerformed(java.awt.event.ActionEvent evt) {
        jComboBox1.setEnabled(false);
        jTextFieldPlayer1.setText("");
        jTextFieldPlayer1.setEditable(true);
    }

    private void jRadioButton1noActionPerformed(java.awt.event.ActionEvent evt) {
        jComboBox1.setEnabled(true);
        initComboBox1();
        jTextFieldPlayer1.setText("");
        jTextFieldPlayer1.setEditable(false);
    }

    private void jRadioButton2yesActionPerformed(java.awt.event.ActionEvent evt) {
        jComboBox2.setEnabled(false);
        jTextFieldPlayer2.setText("");
        jTextFieldPlayer2.setEditable(true);
    }

    private void jRadioButton2noActionPerformed(java.awt.event.ActionEvent evt) {
        jComboBox2.setEnabled(true);
        initComboBox2();
        jTextFieldPlayer2.setText("");
        jTextFieldPlayer2.setEditable(false);
    }

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {
        if (jComboBox1.getSelectedItem() != null) {
            jTextFieldPlayer1.setText(jComboBox1.getSelectedItem().toString());
        }
    }

    private void jComboBox4ActionPerformed(java.awt.event.ActionEvent evt) {
        if (jComboBox2.getSelectedItem() != null) {
            jTextFieldPlayer2.setText(jComboBox2.getSelectedItem().toString());
        }
    }

    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Game.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Game().setVisible(true);
            }

        });
    }

}
