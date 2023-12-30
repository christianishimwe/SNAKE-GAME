/**
* Name: Christian Ishimwe
* Pennkey: ishimwe
* Execution: java SnakeGame
*
* Description: this program presents a snake game. It initally starts with
* one segment of snake and one food item. Pressing keys "a",
*  "s", "d", or "w" moves the snake in different directions.
* Then snake eats the food as long as it collides with it and
* another food item immediately appears somewhere else while
* the snake grows in length. Game ends when the snake collides
* with itself or the wall
**/
public class SnakeGame {
    public static void main(String[] args) {
        int width = 600;
        int height = 600;
        
        PennDraw.setCanvasSize(width, height);
        PennDraw.setXscale(0, width);
        PennDraw.setYscale(0, height);
        PennDraw.enableAnimation(5);
        
        //creates a rectangular board on which the game is played
        Board board = new Board(width - 100, height - 100);
        
        // this infinite loop runs the whole game
        while (true) {
            /*check if the game is over. If it is over,
            the user is allowed to press "k",
            so as to reset the game*/
            if (board.gameIsOver()) {
                PennDraw.clear();
                PennDraw.text(300, 300,
                "Game Over!" + " " + "Your Score is" + " " + board.getScore());
                if (PennDraw.hasNextKeyTyped()) {
                    String theKey = "";
                    theKey += PennDraw.nextKeyTyped();
                    if (theKey.equals("k")) {
                        board = new Board(width - 100, height - 100);
                        PennDraw.enableAnimation(5);
                    }
                }
                PennDraw.advance();
                
                /* if the game is running, the user is allowed to
                * press "m", to toggle between
                normal and hard mode*/
            } else {
                if (board.getNormalMode()) {
                    PennDraw.enableAnimation(5);
                } else if (board.getHardMode()) {
                    PennDraw.enableAnimation(10);
                }
                PennDraw.clear();
                board.update();
                board.draw();
                PennDraw.advance();
            }
        }
    }
}
