#include "event.h"

// Creates a Event struct given the hour, minute, and second, and event id
//   param hour: int representing the hour 0-23
//   param min: int representing the minute 0-59
//   param sec: int representing the second 0-59
//   param event_id: int representing the event id >= 0
//   return: a Event struct with event id and its time
// TODO: complete the function
struct Event create_new_event(int hour, int min, int sec, int event_id) {
  struct Event ret;
  ret.event_id  = event_id;
  ret.st.hour = hour;
  ret.st.min = min;
  ret.st.sec = sec;
  return ret;
}

// Gets the hour from a Time struct
//   param t: struct representing a time
//   return: an int representing the hour of time t
// TODO: complete the function
int get_hour(struct Time t) {
  int hour = t.hour;
  return hour;
}

// Gets the minute from a Time struct
//   param t: struct representing a time
//   return: an int representing the minute of time t
// TODO: complete the function
int get_min(struct Time t) {
  int min = t.min;
  return min;
}

// Gets the second from a Time struct
//   param t: struct representing a time
//   return: an int representing the second of time t
// TODO: complete the function
int get_sec(struct Time t) {
  int sec = t.sec;
  return sec;
}

// Gets the event id from a Event struct
//   param e: struct representing a event
//   return: an int representing the id of event e
// TODO: complete the function
int get_event_id(struct Event e) {
  int id = e.event_id;
  return id;
}

// Gets the time from a Event struct
//   param e: struct representing a event
//   return: a Time struct representing the time of event e
// TODO: complete the function
struct Time get_event_time(struct Event e) {
  struct Time ret;
  ret = e.st;
  return ret;
}

// Creates a Time struct representing the time difference between two events of
// today
//   param e1: Event struct representing the first event
//   param e2: Event struct representing the second event
//   return: Time struct representing time between e1 and e2
// TODO: complete the function
struct Time elapsed_time(struct Event e1, struct Event e2) {
  struct Time ret;
  struct Time t1 = e1.st;
  struct Time t2 = e2.st;
  if(t1.hour > t2.hour){
    ret.hour = t1.hour -t2.hour;
    ret.min = t1.min - t2.min;
    ret.sec = t1.sec - t2.sec;
    if(ret.sec < 0){
      ret.min -= 1;
      ret.sec += 60;
    }
    if(ret.min < 0){
      ret.hour -= 1;
      ret.min += 60;
    }
  }
  else if(t1.hour < t2.hour){
    ret.hour = t2.hour -t1.hour;
    ret.min = t2.min - t1.min;
    ret.sec = t2.sec - t1.sec;
    if(ret.sec < 0){
      ret.min -= 1;
      ret.sec += 60;
    }
    if(ret.min < 0){
      ret.hour -= 1;
      ret.min += 60;
    }
  }
  else{
    ret.hour = 0;
    if(t1.min > t2.min){
      ret.min = t1.min - t2.min;
      ret.sec = t1.sec - t2.sec;
      if(ret.sec < 0){
	ret.min -=  1;
	ret.sec += 60;
      }
    }
    else if(t2.min > t1.min){
      ret.min = t2.min - t1.min;
      ret.sec = t2.sec - t1.sec;
      if(ret.sec < 0){
	ret.min -= 1;
	ret.sec += 60;
      }
    }
    else{
      ret.min = 0;
      if(t1.sec >= t2.sec){
	ret.sec = t1.sec - t2.sec;
      }
      else{
	ret.sec = t2.sec -t1.sec;
      }
    }
  }
  return ret;
}	
