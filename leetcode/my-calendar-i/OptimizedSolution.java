import java.util.*;

class Interval implements Comparable<Interval> {
   	int start;
   	int end;

   	public Interval(int start, int end) {
      	this.start = start;
      	this.end = end;
   	}

   	public boolean overlap(Interval i){
      	return this.start < i.end && i.start < this.end;
   	}

   	public int compareTo(Interval i) {
   		return this.start - i.start;
   	}
}

class MyCalendar {

	TreeSet<Interval> intervals;

   	public MyCalendar() {
    	this.intervals = new TreeSet<>();
   	}
    
   	public boolean book(int start, int end) {
      	Interval newInterval = new Interval(start, end);
      	Interval prevInterval = this.intervals.floor(newInterval);
      	if (prevInterval != null && prevInterval.overlap(newInterval)) {
      		return false;
      	}

      	Interval nextInterval = this.intervals.ceiling(newInterval);
      	if (nextInterval != null && nextInterval.overlap(newInterval)) {
      		return false;
      	}

      	intervals.add(newInterval);
      	return true;
    }
}