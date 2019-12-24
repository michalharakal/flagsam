# AndroidDevChallenge

![Image of AndroidDevChallenge](https://github.com/michalharakal/flagsam/blob/master/docs/images/adc.png)

## Idea

**Flag Sam** is a fun game for children based on flag semaphore signals

Let us introduce Sam first. Sam is the main character of the game. He or she is helping kids (or adults) to learn the flag
alphabet first. Then they can use it in the game to get points or compete
with one another.

The app will recognize the position of the person from a live camera picture and detect the signal. Ideally it should work without flags too. Recognition is done with a help of a pose estimation vision model.

The app will use simple cartoon drawings and animations to show
detected movement of the person (instead of showing the real camera preview picture). Player can choose from various personas.

![Image of Sam](https://github.com/michalharakal/flagsam/blob/master/docs/images/Sam1.png)
![Image of Sam](https://github.com/michalharakal/flagsam/blob/master/docs/images/Sam2.png)
![Image of Sam](https://github.com/michalharakal/flagsam/blob/master/docs/images/Sam3.png)

## The plan

### Starting position

Current implementation of the code is based on the tensorflow lite example called `Posenet`.

It is in a very early stage and the complete code is on github <https://github.com/michalharakal/flagsam>

I am convinced that because of the simplicity of the idea it can be finished on time.

### How Google could help us achieve the goal

1.  with knowledge how to fine tune the model
2.  to provide help with edge cases, like dealing with various cameras
3.  ideas with testing strategies
4.  some hints regarding marketing and Play Store
    <br/>

### Timeline

**December 2019:**

-   Prototype is working

**January  2020:**

-   cartoon engine (selected an existing one or write our own renderer)
-   Design, UX and graphics
-   animations and cartoons redering

**February 2020 :**

-   polishing on design and graphical assets
-   Implemented game rules

**March 2020 :**

-   Testing and Beta Program

**April 2020 :**

-   Testing and Deployment

## About developer

My name is Michal Harakal, I am an Android developer working for Deutsche Telekom. I am speaking Android from the very beginning. My first app I was working on was Android client for project `Wheelmap.org`, which is showing information about accesibility of points of interesets for wheelchair users. This information can be also added and is stored in the Open Street map database. My latest open source project is a mobile conference app for project Dukecon.org, already used for various conferences. Native App is written in Kotlin and is being ported into Kotlin Multiplatfrom so we can have an iOS client too. Kotlin Multiplatform is so interesting for me, that I was speaking about it recently on various Meetups and on conference.
