package de.wiener;

import static com.raylib.Jaylib.*;

public class main{
    public static void main(String[] args){
        int screenWidth = 800;
        int screenHeight = 450;
        InitWindow(screenWidth, screenHeight, "jaylib");
        SetTargetFPS(60);
        
        
        float xspeed = 4;
        float yspeed = 4;

        int player1Score = 0;
        int player2Score = 0;
        
        Rectangle player1 = new Rectangle(20, screenHeight/2, 20, 90);
        Rectangle player2 = new Rectangle(750, screenHeight/2, 20, 90);
        Rectangle ball = new Rectangle(screenWidth/2, screenHeight/2, 20, 20);

        while(!WindowShouldClose()){

            //Update
            //--------------------------------------------------------
            if(IsKeyDown(KEY_W)) player1.y(player1.y()-3f);
            if(IsKeyDown(KEY_S)) player1.y(player1.y()+3f);
            if(IsKeyDown(KEY_DOWN)) player2.y(player2.y()+3f);
            if(IsKeyDown(KEY_UP)) player2.y(player2.y()-3f);
            if(IsKeyDown(KEY_SPACE)){ ball.x(screenWidth/2); ball.y(screenHeight/2);}

            ball.x(ball.x() + xspeed);
            ball.y(ball.y() + yspeed);

            if(ball.x() > 800){
                ball.x(screenWidth/2);
                ball.y(screenHeight/2);
                player1Score += 1;
            }
            if(ball.x() <= 0){
                ball.x(screenWidth/2);
                ball.y(screenHeight/2);
                player2Score += 1;
            }
            if(ball.y() >= 450){
                yspeed *= -1;
            }
            if(ball.y() <= 0){
                yspeed *= -1;
            }

            if(CheckCollisionRecs(ball, player1)) {xspeed *= -1; ball.x(player1.x()+player1.width());}
            if(CheckCollisionRecs(ball, player2)) {xspeed *= -1; ball.x(player2.x()-20);}



            //Draw
            //--------------------------------------------------------
            BeginDrawing();
            ClearBackground(BLACK);
            DrawRectangleRec(player1, WHITE);
            DrawRectangleRec(player2, WHITE);
            DrawRectangleRec(ball, WHITE);
            DrawText(Integer.toString(player1Score), 250, 50, 30, WHITE);
            DrawText(Integer.toString(player2Score), 450, 50, 30, WHITE);
            for(int i = 0; i < 450; i+=30){ if((i/10)%2 == 0 ){DrawRectangle((screenWidth/2)-5, i, 10, 30, WHITE);}}

            EndDrawing();
        }
        CloseWindow();
    }
}

