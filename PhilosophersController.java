

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PhilosophersController {

    @FXML
    private ImageView imageTable;

    @FXML
    private ImageView phil1;

    @FXML
    private ImageView phil2;

    @FXML
    private ImageView phil3;

    @FXML
    private ImageView phil4;

    @FXML
    private ImageView phil5;
    
    private  Image eatPhil = new Image(getClass().getResourceAsStream("eatPhil.png"));
    private  Image notEatPhil = new Image(getClass().getResourceAsStream("פילוסוף.png"));
    
    
    private boolean[] freeSticks = new boolean[5];//flags for each stick if free to use.
	private int[] eatCount = new int[5];//counter for the times each philosopher ate. 
    private PhilMonitor  m;
	private PhilThread[] phils = new PhilThread[5];
    private boolean stop = false;
    
    @FXML
   	public void initialize() {
    	//initializing variables.
    	
    	for(int i=0; i<5; i++)
    		freeSticks[i] =true;
    	
    	for(int i=0; i<5; i++)
    		eatCount[i] = 0;
    	
    	m= new PhilMonitor (freeSticks, eatCount);
    	stop =false;
    	
    	for(int i=0; i<5; i++) 
    		phils[i] =new PhilThread(i, m, this);
    	
    	//start threads.
    	for(int i=0; i<5; i++)
    		phils[i].start();
    	
    }


    @FXML
    void stopAll(ActionEvent event) {
    	stop = true;
    }
    
    public boolean getStop() {
    	return stop;
    }
    
    public void eat(int id) {
    	switch (id) {
		case 0:
			phil1.setImage(eatPhil);
			break;
		
		case 1:
			phil2.setImage(eatPhil);
			break;
	
		case 2:
			phil3.setImage(eatPhil);
			break;
			
		case 3:
			phil4.setImage(eatPhil);
			break;

		default:
			phil5.setImage(eatPhil);
			break;
		}
    }
    	
    	public void notEat(int id) {
        	switch (id) {
    		case 0:
    			phil1.setImage(notEatPhil);
    			break;
    		
    		case 1:
    			phil2.setImage(notEatPhil);
    			break;
    	
    		case 2:
    			phil3.setImage(notEatPhil);
    			break;
    			
    		case 3:
    			phil4.setImage(notEatPhil);
    			break;

    		default:
    			phil5.setImage(notEatPhil);
    			break;
    		}
    }

}
