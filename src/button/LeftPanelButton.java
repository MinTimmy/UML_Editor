package button;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.Config;
import main.MCanvas;
import main.MCanvasMouseListener;
import status.SelectStatus;
import status.lineStatus.AssociationLineStatus;
import status.lineStatus.CompositionLineStatus;
import status.lineStatus.GeneralizationLineStatus;
import status.objectStatus.ClassStatus;
import status.objectStatus.UseCaseStatus;

public class LeftPanelButton extends JPanel{
    private static JButton[] buttons;
    private int width;
    private int height;
    private Config.AllState status;

    public LeftPanelButton(){
        super();
        LeftPanelButton.buttons = new JButton[Config.AllState.values().length];
        this.width = Config.leftPanelButtonWidth;
        this.height = Config.leftPanelButtonHeight;
        status = Config.AllState._select;

        makeButton();
        buttons[status.ordinal()].setBackground(Color.RED);
        this.setLayout(new GridLayout(6, 1));
        this.setVisible(true);
    }

    private void makeButton(){
        for (Config.AllState i : Config.AllState.values()) {
            if (i == Config.AllState.None) continue;
            buttons[i.ordinal()] = new JButton();
            buttons[i.ordinal()].setIcon(new ImageIcon(Config.buttonImagePath + Config.buttonImageName[i.ordinal()]));
            buttons[i.ordinal()].setOpaque(true);
            buttons[i.ordinal()].setBounds(0,0,width,height);
            buttons[i.ordinal()].setVisible(true);
            buttons[i.ordinal()].addActionListener(new ButtonClickListener(i));
            buttons[i.ordinal()].addMouseListener(new LeftPanelButtonMouseListener(i));
            this.add(buttons[i.ordinal()]);
        }
    }

    private class ButtonClickListener implements ActionListener {
        private Config.AllState index;
        public ButtonClickListener(Config.AllState i) {
            this.index = i;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            MCanvas.getInstance().unselectAllObject();
            status = index;
            if (index == Config.AllState._select){
                MCanvasMouseListener.getInstance().setStatus(new SelectStatus());
            }
            if (index == Config.AllState._class){
                MCanvasMouseListener.getInstance().setStatus(new ClassStatus());
            }
            if (index == Config.AllState._use_case){
                MCanvasMouseListener.getInstance().setStatus(new UseCaseStatus());
            }
            if (index == Config.AllState._association_line){
                MCanvasMouseListener.getInstance().setStatus(new AssociationLineStatus());
            }
            if(index == Config.AllState._generalization_line){
                MCanvasMouseListener.getInstance().setStatus(new GeneralizationLineStatus());
            }
            if(index == Config.AllState._composition_line){
                MCanvasMouseListener.getInstance().setStatus(new CompositionLineStatus());
            }
            setClicked();
        }
        private void setClicked(){
            for (JButton i : LeftPanelButton.buttons) {
                if (i == null) continue;
                i.setBackground(Color.WHITE);
            }
            buttons[this.index.ordinal()].setBackground(Color.RED);
        }
    }

    private class LeftPanelButtonMouseListener extends MouseAdapter{
        private Config.AllState index;
        public LeftPanelButtonMouseListener(Config.AllState i) {
            super();
            index = i;
        }
       @Override
        public void mouseEntered(MouseEvent e) {
            // Change background color when mouse enters
            buttons[index.ordinal()].setBackground(Color.RED);
        }
        
        @Override
        public void mouseExited(MouseEvent e) {
            // Change background color back when mouse exits
            buttons[index.ordinal()].setBackground(Color.WHITE);
            buttons[status.ordinal()].setBackground(Color.RED);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    } 
}
