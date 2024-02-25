class Interval {
   int start;
   int end;

   public (int start, int end) {
      this.start = start;
      this.end = end;
   }

   public boolean overlap(Interval i){
      return this.start < i.end && i.start < this.end;
   }
}

class MyCalendar {

	List<Interval> intervals;

   public MyCalendar() {
      this.intervals = new ArrayList<>();
   }
    
   public boolean book(int start, int end) {
      Interval newInternal = new Interval(start, end);
      for (Interval interval: intervals) {
       	if (interval.overlap(newInterval)) {
       		return false;
       	}
      }
      this.intervals.add(newInterval);
      return true;
   }
}