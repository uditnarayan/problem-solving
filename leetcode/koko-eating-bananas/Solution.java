class Solution {
    public int minEatingSpeed(int[] piles, int h) {  
        int speed = 1;
        while (true) {
            int time = 0;
            for (int pile: piles) {
                time += Math.ceil(pile/speed);
                if (time > h) break;
            }

            if (time <= h) {
                return speed;
            }   else {
                speed++;
            }
        }
    }
}