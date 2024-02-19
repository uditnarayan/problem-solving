class BinSearchSolution {
	public int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = 0;
        for (int pile: piles) {
        	high = Math.max(high, pile);
        }

        while (low < high) {
        	int time = 0;
        	int speed = (high + low) / 2;
        	for (int pile: piles) {
	        	time += Math.ceil((double)pile/speed);
	        }

	        if (time <= h) {
                high = speed;
            }   else {
                low = speed + 1;
            }
        }
        return high;
    }
}