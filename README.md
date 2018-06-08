# AndroidLifecycles
A simple app demonstrating when different states of an android app are called.

Implements a RecyclerView to record each instance of a cycle transition.
Whenever the used takes actions like minimizing the app, closing the app, or opening the app,
and state calls are recorded and a toast is sent to the screen. 

This app was designed as a supplement when explaining the Android application lifecycle, and
is used to give a visual understading of when each lifecycle transition method 
(Create, Start, Resume, Pause, Stop, and Destroy) are called. 

Additionally, the app makes use of the Android Library Butterknife to remove boilerplate code. 
