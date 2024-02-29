package component.object;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.FontMetrics;

import main.Config;

public class UseCaseObject extends Object{
    public UseCaseObject(Point location) {
        super(location);
        this.width = Config.use_case_width;
        this.height = Config.use_case_height;

        this.labelText = "Use case";
        this.setBounds(
            this.getLocation().x - (this.width/2), 
            this.getLocation().y - (this.height/2),
            this.width, 
            this.height
        );
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(this.width, this.height));
    } 


    @Override
   protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(Color.BLACK);
        g2d.drawOval(0,  0, this.width, this.height);


        // Set Label
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int labelWidth = fontMetrics.stringWidth(this.labelText);
        int labelHeight = fontMetrics.getHeight();
        int centerX = (this.width - labelWidth) / 2;
        int centerY = (this.height - labelHeight) / 2 + fontMetrics.getAscent();
        g2d.drawString(this.labelText, centerX, centerY);

        if (isSelected) {
            g2d.setColor(Config.dot_color);
            g2d.fillRect((this.width/2)   , 0, Config.dot_width, Config.dot_heigh); // upper
            g2d.fillRect(0              , (this.height/2), Config.dot_width, Config.dot_heigh); // left
            g2d.fillRect(this.width -Config.dot_width  , (this.height/2), Config.dot_width, Config.dot_heigh); // right
            g2d.fillRect((this.width/2)   , this.height-Config.dot_heigh, Config.dot_width, Config.dot_heigh); // bottom
        }

    }
}
