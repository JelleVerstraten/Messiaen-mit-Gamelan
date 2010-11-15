/*

Messiaen mit Gamelan
by Jelle Verstraten en Kieran Klaassen

A piece for recorder and live electronics

Version log:
12-11-2010: 0.1, historisch moment, de eerste tonen!!!
15-11-2010: Een bufferrecorder en player ingevoegd! jeej, nu nog alleen het triggeren?

*/



//SynDefs

//sine voor additieve synth
SynthDef(\SineEnv,{|freq=440, at=0.1, al=0.1, dt=0.1, sl=0.1, rt=1,az=0|
	var chan=8;
	Out.ar(0,	
		PanAz.ar(chan, SinOsc.ar(freq,0, EnvGen.ar( Env.new([0,al,sl,0],[at,dt,rt]) , doneAction:2) ), az) ;
	);	
}).add;

// Buffer Recorder
SynthDef(\bufRecorder,{ |out=0, buf=0, fade=0.1, level=1, xfade=0, run=1, vol=1|
	var in;
	in = SoundIn.ar(1, EnvGen.kr(
			Env.new([0,1,1,0], [fade, d-(fade*2), fade], 'linear'), doneAction: 2)
	) * vol;
	RecordBuf.ar(in, buf, 0, level, xfade, run);
}).add;

//Buffer Player:
SynthDef(\bufPlayer, { | bufnum = 0|
	var playbuf;
	playbuf = PlayBuf.ar(1,bufnum);
	FreeSelfWhenDone.kr(playbuf); // frees the synth when the PlayBuf is finished
	Out.ar(0, playbuf);
}).play(s, [\bufnum, b]);


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
	


