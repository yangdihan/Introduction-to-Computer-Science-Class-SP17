/**
 * @author tanwisu2,dyang42,xintong2
 */
public class RainGame {

	public static void main(String[] args) {
		// To get points type your netids above (CORRECTLY-Please double check your partner correctly spells your netid correctly!!)
		// Your netid is the unique part of your @illinois email address
		// Do not put your name or your UIN. 
		// REMEMBER TO COMMIT this file...
	
		int x=0, y=0, dx=0, dy=0;
		double score = 0;
		double level = 0;
		String text = "";
		
		
		Zen.setFont("Impact-32");
		long beforeStart =System.currentTimeMillis();
		double wait = 0;
		while (wait <= 5000){
			Zen.setColor(0, 0, 0);
			Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());
			Zen.setColor(255, 100, 0);
			Zen.drawText("Hey", 100, 50);
			Zen.drawText("陆韬羽", 100, 100);
			Zen.drawText("This Game", 100, 150);
			Zen.drawText("Might Make You", 100, 200);
			Zen.setColor(0, 255, 0);
			Zen.drawText("BLIND", 100, 250);
			Zen.setColor(255, 100, 0);
			Zen.drawText("Too", 100, 300);
			Zen.drawText("Be Careful", 100, 350);
			Zen.drawText("Don't Get Addicted", 100, 400);
			wait = System.currentTimeMillis() - beforeStart;
		}
		
		Zen.setColor(0, 0, 0);
		Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());
		long waitStart =System.currentTimeMillis();
		double waitAgain = 0;
		while (waitAgain <= 3000){
			Zen.setColor(255, 100, 0);
			Zen.drawText("During Game", 100, 50);
			Zen.drawText("Press", 100, 100);
			Zen.setColor(0, 255, 0);
			Zen.drawText("+", 100, 250);
			Zen.setColor(255, 100, 0);
			Zen.drawText("To", 100, 300);
			Zen.drawText("Level UP", 100, 350);
			Zen.drawText("!!!!", 100, 400);
			waitAgain = System.currentTimeMillis() - waitStart;
		}
	
		while (Zen.isRunning()) {
			if(Zen.isKeyPressed('+')||Zen.isKeyPressed('='))
				score += 100;
			
			long startTime =System.currentTimeMillis();
			Zen.flipBuffer();
			if (text.length() == 0) {
				x = 320;
				y = 0;//Zen.getZenHeight() / 2;
				if (level > 3)
					dx = (int)(Math.random()*level);
				else
					dx = 0;
				dy = (int)level + 1;
				text = "" + (int) (Math.random() * 999);
				
			}
			Zen.setColor(0, 0, 0);
			Zen.fillRect(0, 0, Zen.getZenWidth(), Zen.getZenHeight());

			Zen.setColor(0, 255, 0);
			Zen.drawText(text, x, y);
			
			Zen.drawText("Level: " + (int)level,10,30);
			Zen.drawText("Score: " + (int)score,10,60);
			
			if((level  > 2) && (System.currentTimeMillis() % 30 == 0)){
				Zen.drawText("Hello",(int)(Math.random()*500), (int)(Math.random()*500));
				if((level  > 7) && (System.currentTimeMillis() % 30 == 0)){
					Zen.drawText("苟利国家生死以",(int)(Math.random()*500), (int)(Math.random()*500));
					Zen.drawText("岂因福祸避趋之",(int)(Math.random()*500), (int)(Math.random()*500));
					if((level  > 12) && (System.currentTimeMillis() % 30 == 0)){
						Zen.drawText("我是一只小老鼠\ngigigigigi",(int)(Math.random()*500), (int)(Math.random()*500));
						Zen.drawText("most likely不会change",(int)(Math.random()*500), (int)(Math.random()*500));
						if((level  > 20) && (System.currentTimeMillis() % 30 == 0)){
							Zen.setColor(255, 100, 100);
							Zen.drawText("我爱陆韬羽嘻嘻嘻嘻嘻",(int)(Math.random()*500), (int)(Math.random()*500));
							Zen.drawText("乖巧得像个小猪仔",(int)(Math.random()*500), (int)(Math.random()*500));
						}
					}
				}
			}
			x = (x + dx)%640;
			y = (y + dy)%480;
			
			
			// Find out what keys the user has been pressing.
			String user = Zen.getEditText();
			// Reset the keyboard input to an empty string
			// So next iteration we will only get the most recently pressed keys.
			Zen.setEditText("");
			
			for(int i=0;i < user.length() && user.length() < 4;i++) {
				char c = user.charAt(i);
				if(c == text.charAt(0))
					text = text.substring(1,text.length()); // all except first character
			}
			if (text.length() == 0){
				long elapsed = System.currentTimeMillis() - startTime;
				startTime = System.currentTimeMillis();
				score += 3000 / elapsed;
				level = Math.floor(score/1000);
			}
			
			Zen.sleep(10);// sleep for 90 milliseconds

		}
	}

}
