package gunduel;

public class Duel {
    
    // result matrix
    private static final Result[][] resultMatrix = 
    {
        {Result.DRAW, Result.BWIN, Result.DRAW},
        {Result.AWIN, Result.DRAW, Result.DRAW},
        {Result.DRAW, Result.DRAW, Result.DRAW}
    };
    
    // internal game stats
    private static short playerAAmmo = 0;
    private static short playerBAmmo = 0;
    private static Result result = Result.DRAW;
    private static boolean forceEnd = false;
    
    // config
    private static final short rounds = 100;
    private static short turn = 1;
    
    // players
    private static MathPlayer playerA;
    private static RandomPlayer playerB;

    public static void main(String []args) {
        playerA = new MathPlayer();
        playerB = new RandomPlayer();
        gameloop();
    }
    
    private static void gameloop () {
        // for number of rounds perform turns
        while(!forceEnd && turn <= rounds && result == Result.DRAW) {
            turn();
            turn++;
        }
    }
    
    // actual game logic
    private static void turn () {
        boolean playerAValidAction = true;
        boolean playerBValidAction = true;
        
        System.out.println(turn + ":\tA("+playerAAmmo+") \tB("+playerBAmmo+")");
        
        // receive players actions and inform them
        Action playerAAction = playerA.nextAction();
        Action playerBAction = playerB.nextAction();
        // this call could also be done or eventually avoided after the action validity check
        playerA.perceive(playerBAction);
        playerB.perceive(playerAAction);
        
        // check for validity of moves and publish result
        playerAValidAction = (playerAAmmo == 0 && playerAAction == Action.FIRE) ? false : true;
        playerBValidAction = (playerBAmmo == 0 && playerBAction == Action.FIRE) ? false : true;
        if (playerAValidAction && playerBValidAction) {
            result = resultMatrix[playerAAction.ordinal()][playerBAction.ordinal()];
        } else {
            if (playerAValidAction && !playerBValidAction) {
                result = Result.AWIN;
            } else if (!playerAValidAction && playerBValidAction) {
                result = Result.BWIN;
            } else {
                result = Result.DRAW;
            }
            forceEnd = true;
        }
        print(turn, playerAAction, playerBAction, result);
        
      //update stats
        if (playerAAction == Action.LOAD) {
            playerAAmmo++;
        }
        if (playerAAction == Action.FIRE && playerAAmmo > 0) {
            playerAAmmo--;
        }
        if (playerBAction == Action.LOAD) {
            playerBAmmo++;
        }
        if (playerBAction == Action.FIRE && playerBAmmo > 0) {
        	playerBAmmo--;
        }
    }
    
    private static void print (short turn, Action playerAAction, Action playerBAction, Result r) {
        System.out.println(turn + ":\tA->" + playerAAction + "\tB->" + playerBAction + " ==> " + r);
    }
}
