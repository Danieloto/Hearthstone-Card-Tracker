package test;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class CropImg {

	    public static void main(String[] args){
	        EventQueue.invokeLater(new Runnable()
	        {
	            public void run(){
	                ImageFrame frame = new ImageFrame();
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.setVisible(true);


	            }
	        }
	        );
	    }
	}

	class ImageFrame extends JFrame{

	    public ImageFrame(){
	        setTitle("ImageTest");
	        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

	        ImageComponent component = new ImageComponent();
	        add(component);

	    }

	    public static final int DEFAULT_WIDTH = 286 + 75;
	    public static final int DEFAULT_HEIGHT = 420;
	}


	class ImageComponent extends JComponent{
	    /**
	     * 
	     */
	    private static final long serialVersionUID = 1L;
	    private Image image;
	    public ImageComponent(){
	        try{
	            File image2 = new File("images/1003.png");
	            image = ImageIO.read(image2);

	        }
	        catch (IOException e){
	            e.printStackTrace();
	        }
	    }
	    
	    public void paintComponent (Graphics g){
	    	
	    	ImageIcon icon = new ImageIcon("images/1004.png");
	       
//	    		//for image cropping - label only
//	    	
	        Image image_disp = icon.getImage();
	        image_disp = createImage(new FilteredImageSource(image_disp.getSource(), new CropImageFilter(0, 188, 286, 50)));
	    		ImageIcon icon2 = new ImageIcon(image_disp);
	    		
	    	Image image_disp2 = icon.getImage();
	    	image_disp2 = createImage(new FilteredImageSource(image_disp2.getSource(), new CropImageFilter(0, 35, 75, 50)));
    			ImageIcon icon3 = new ImageIcon(image_disp2);
    			
    		
	            
	    		//for image cropping - label + image
 
//	        Image image_disp = icon.getImage();
//	        image_disp = createImage(new FilteredImageSource(image_disp.getSource(), new CropImageFilter(80, 60, 120, 190)));
//	    		ImageIcon icon2 = new ImageIcon(image_disp);
	            
	    		
	    		//display image
	    		
		    super.paintComponent(g);
		    icon2.paintIcon(this, g, 0, 0);
		    icon3.paintIcon(this, g, 270, 0);
	            
	            
//	        if(image == null) return;
//	        //int imageWidth = image.getWidth(this);
//	        int imageWidth = 286;
//	        //int imageHeight = image.getHeight(this);
//	        int imageHeight = 395;
//
//	        g.drawImage(image, 0, 0, this);
//
//	        for (int i = 0; i*imageWidth <= getWidth(); i++)
//	            for(int j = 0; j*imageHeight <= getHeight();j++)
//	                if(i+j>0) g.copyArea(0, 0, imageWidth, imageHeight, i*imageWidth, j*imageHeight);
	    }

	    
    }
