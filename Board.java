/**
* Name: Christian Ishimwe
* Pennkey: ishimwe
* Execution: N/A this class is used by snakeGame.java for its implementation
*
* Description: this class includes all methods for the board of the game
**/
public class Board {
    
    private int numRows, numCols, score; // divides the board into colums and rows
    private double width, height, cellWidth, cellHeight;
    private Snake snake;
    private FoodItem foodItem;
    private BonusFoodItem bonusFoodItem;
    
    public Board(double width, double height) {
        this.width = width;
        this.height = height;
        numRows = 20;
        numCols = 20;
        cellWidth = width / numRows;
        cellHeight = height / numCols;
        snake = new Snake(18, 18, cellWidth, cellHeight);
        foodItem = new FoodItem(3, 3, cellWidth);
        bonusFoodItem = null;
        
    }
    
    /**
    * Inputs: void
    * Outputs: void
    * Description: this method updates the structure of the board
    * whenever the snake, food item, or bonus food item change
    * their positions and when the snake grows in size
    */
    public void update() {
        /*before creating a new bonus food item, this function
        * checks if we don't already have a bonus food item or if
        * we already hit our current food item*/
        createAbonusWhenAppropriate();
        
        /* checks if the snake collides with either the food item
        * or bonus food item */
        collisionHappened();
        snake.update();
        foodItem.update(numCols, numRows, snake, bonusFoodItem);
        
    }
    
    /**
    * Inputs: void
    * Outputs: void
    * Description: this method draws the board
    */
    public void draw() {
        PennDraw.setPenColor(144, 238, 144);
        PennDraw.filledRectangle(300, 300, width / 2, height / 2);
        PennDraw.setPenColor(0, 0, 0);
        PennDraw.rectangle(300, 300, width / 2, height / 2);
        snake.draw();
        foodItem.draw();
        
        /*draws the bonus foooIitem only if we never had one before or
        if the previous one is hit*/
        if (bonusFoodItem != null) {
            if (!bonusFoodItem.getIsHit()) {
                bonusFoodItem.draw();
            }
        }
        drawScore();
    }
    
    /**
    * Inputs: void
    * Outputs: void
    * Description: this method checks if the snake has collided
    * with the food item or the bonus food item
    */
    private void collisionHappened() {
        if (snake.getHeadCol() == foodItem.getCol() &&
        snake.getHeadRow() == foodItem.getRow()) {
            foodItem.setIsHit(true);
            score++;
            snake.handleSegments(); // creates a tail segment after collision
        } else {
            foodItem.setIsHit(false);
        }
        if (bonusFoodItem != null) {
            if (snake.getHeadCol() == bonusFoodItem.getCol() &&
            snake.getHeadRow() == bonusFoodItem.getRow()) {
                bonusFoodItem.setIsHit(true);
                score = score + 5;
                snake.handleSegments(); // creates a tail segment after collision
            }
        }
    }
    
    /**
    * Inputs: void
    * Outputs: void
    * Description: this method draws the current score along
    * the perimeter of the board
    */
    private void drawScore() {
        String yourScore = "Score: " + score;
        PennDraw.text(500, 570, yourScore);
    }
    
    /**
    * Inputs: void
    * Outputs: void
    * Description: this method reports  whether the snake either hit
    * itself or went off limits of the board
    */
    public boolean gameIsOver() {
        if (snake.snakeWentRestricted()) {
            return true;
        }
        return false;
    }
    
    /**
    * Inputs: void
    * Outputs: int
    * Description: this is a getter method for score
    */
    public int getScore() {
        return score;
    }
    
    /**
    * Inputs: void
    * Outputs: boolean
    * Description: this is a getter method for checking whether the snake is
    * currently in normal mode
    */
    public boolean getNormalMode() {
        return snake.getNormalMode();
    }
    
    /**
    * Inputs: void
    * Outputs: boolean
    * Description: this is a getter method for checking whether the snake is
    * currently in hard mode
    */
    public boolean getHardMode() {
        return snake.getHardMode();
    }
    
    /**
    * Inputs: void
    * Outputs: void
    * Description: this method creates a new bonus item
    */
    private void createAbonusWhenAppropriate() {
        /* to create a new bonus item, we check if we never had one before,
        or if the previous one was hit by the snake*/
        if (bonusFoodItem == null || bonusFoodItem.getIsHit()) {
            double probability = Math.random();
            
            /*this statement evaluates a probability that allows us to
            only create a bonus food item at random intervals during the game*/
            if (probability > 0.10 && probability < 0.20) {
                boolean emptyCellIsNotFound = true;
                
                int bonusCol = 0;
                int bonusRow = 0;
                //int i = 0;
                
                /*this loop allows to only put the bonus food item in an
                empty cell i.e: a cell that is not occupied by either
                the snake or the food item*/
                while (emptyCellIsNotFound) {
                    boolean haveAwrongCell = false;
                    
                    bonusCol = (int) (Math.random() * (numCols + 1));
                    bonusRow = (int) (Math.random() * (numRows + 1));
                    if (bonusCol == 0 || bonusRow == 0) {
                        emptyCellIsNotFound = true;
                    } else if (bonusCol == snake.getHeadCol() &&
                    bonusRow == snake.getHeadRow()) {
                        emptyCellIsNotFound = true;
                        haveAwrongCell = true;
                    } else if (bonusCol == foodItem.getCol() &&
                    bonusRow == foodItem.getRow()) {
                        emptyCellIsNotFound = true;
                        haveAwrongCell = true;
                    } else if (bonusCol == foodItem.getCol() &&
                    bonusRow == foodItem.getRow()) {
                        emptyCellIsNotFound = true;
                        haveAwrongCell = true;
                    } else {
                        /*checks whether the position of the bonus
                        food doesn't concide with one of the snake's segments*/
                        for (int i = 0; i < snake.getSegments().size(); i++) {
                            if (bonusCol ==
                            snake.getSegments().get(i).getCurrentCol() &&
                            bonusRow ==
                            snake.getSegments().get(i).getCurrentRow()) {
                                emptyCellIsNotFound = true;
                                haveAwrongCell = true;
                                i = snake.getSegments().size() + 1;
                            }
                        }
                        
                        if (haveAwrongCell) {
                            continue;
                        } else {
                            emptyCellIsNotFound = false;
                        }
                    }
                    
                }
                
                bonusFoodItem = new BonusFoodItem(bonusCol, bonusRow, cellWidth);
            }
        }
        
    }
    
}
