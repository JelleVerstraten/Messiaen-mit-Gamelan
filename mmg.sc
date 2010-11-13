/*

Messiaen mit Gamelan
by Jelle Verstraten en Kieran Klaassen

A piece for recorder and live electronics

Version log:
12-11-2010: 0.1, historisch moment, de eerste tonen!!!

*/



//SinDefs

SynthDef(\SineEnv,{|freq=440, at=0.1, al=0.1, dt=0.1, sl=0.1, rt=1,az=0|
	var chan=8;
	Out.ar(0,	
		PanAz.ar(chan, SinOsc.ar(freq,0, EnvGen.ar( Env.new([0,al,sl,0],[at,dt,rt]) , doneAction:2) ), az) ;
	);	
}).add;


//Definier additieve geluiden:

var demung = (	ratio:[1.0, 1.611, 3.458, 5.142], 
						att:[0.03, 0.04, 0.009, 0.005], 
						al:[0.339, 0.04, 0.009, 0.005],
						dt: 0!4, 
						sl:[0.339, 0.04, 0.009, 0.005], 
						rt:[4.0, 2.25, 2.7, 1.2]
					);
		
//Functies


	var instr = demung;
	var freq = 440;
	instr.ratio.size.do({|i|
		Synth(\SineEnv,[\freq,freq*instr.ratio[i] ,\at, instr.att[i], \al, instr.al[i], \dt, instr.dt[i], \sl, instr.sl[i], \rt, instr.rt[i] ]);
	});
	


