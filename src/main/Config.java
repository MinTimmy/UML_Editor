package main;

import java.awt.Color;

public class Config {
    public static int frame_width = 1200;
    public static int frame_height = 800;

    public static int leftPanelButtonWidth = 600;
    public static int leftPanelButtonHeight = 800;


    // public static String imagePath = "..\\res\\";
    public static String imagePath = "res\\";
    public static String buttonImagePath = imagePath + "button_icon\\";
    public static String[] buttonImageName = {
        "select.png",
        "association_line.png",
        "generalization_line.png",
        "composition_line.png",
        "class.png",
        "use_case.png",
    };
    public static enum AllState  {
        _select,
        _association_line,
        _generalization_line,
        _composition_line,
        _class,
        _use_case,
        None
    };
    public static enum UpperPanelButton{
        _file,
        _edit,
        None
    }
    public static enum DirectionPort{
        _upper,
        _right,
        _bottom,
        _left,
        None
    }
    public static enum ArrowDirection{
        _arrow,
        _tail
    }
    
    public static int class_width = 100;
    public static int class_height = 100;
    public static int use_case_width = 100;
    public static int use_case_height = 50;
    public static int dot_width = 5;
    public static int dot_heigh = 5;
    public static Color dot_color = Color.RED;

    public static AllState whichButtonClicked = AllState.None;
}
