{\rtf1\ansi\ansicpg1252\cocoartf1038\cocoasubrtf320
{\fonttbl\f0\fnil\fcharset0 Monaco;}
{\colortbl;\red255\green255\blue255;\red191\green0\blue0;\red0\green0\blue0;\red191\green0\blue0;
\red0\green0\blue191;\red0\green115\blue0;\red0\green0\blue191;}
\pard\tx560\tx1120\tx1680\tx2240\tx2800\tx3360\tx3920\tx4480\tx5040\tx5600\tx6160\tx6720\ql\qnatural\pardirnatural

\f0\fs34 \cf2 /*\
\
Messiaen mit Gamelan\
by Jelle Verstraten en Kieran Klaassen\
\
A piece for recorder and live electronics\
\
Version log:\
12-11-2010: 0.1, historisch moment, de eerste tonen!!!\
\
*/\cf3 \
\cf4 \
\cf3 \
\
\cf2 //SinDefs\cf3 \
\
\cf5 SynthDef\cf3 (\cf6 \\SineEnv\cf3 ,\{\cf5 |freq=440, at=0.1, al=0.1, dt=0.1, sl=0.1, rt=1,az=0|\cf3 \
	\cf5 var\cf3  chan=8;\
	\cf5 Out\cf3 .ar(0,	\
		\cf5 PanAz\cf3 .ar(chan, \cf5 SinOsc\cf3 .ar(freq,0, \cf5 EnvGen\cf3 .ar( \cf5 Env\cf3 .new([0,al,sl,0],[at,dt,rt]) , doneAction:2) ), az) ;\
	);	\
\}).add;\
\
\
\cf4 //Definier additieve geluiden:\cf0 \
\pard\tx560\tx1120\tx1680\tx2240\tx2800\tx3360\tx3920\tx4480\tx5040\tx5600\tx6160\tx6720\ql\qnatural\pardirnatural
\cf7 \
var\cf0  demung = (	ratio:[1.0, 1.611, 3.458, 5.142], \
						att:[0.03, 0.04, 0.009, 0.005], \
						al:[0.339, 0.04, 0.009, 0.005],\
						dt: 0!4, \
						sl:[0.339, 0.04, 0.009, 0.005], \
						rt:[4.0, 2.25, 2.7, 1.2]\
					);\cf3 \
		\
\pard\tx560\tx1120\tx1680\tx2240\tx2800\tx3360\tx3920\tx4480\tx5040\tx5600\tx6160\tx6720\ql\qnatural\pardirnatural
\cf2 //Functies\cf3 \
\
\
	\cf5 var\cf3  instr = demung;\
	\cf5 var\cf3  freq = 440;\
	instr.ratio.size.do(\{\cf5 |i|\cf3 \
		\cf5 Synth\cf3 (\cf6 \\SineEnv\cf3 ,[\cf6 \\freq\cf3 ,freq*instr.ratio[i] ,\cf6 \\at\cf3 , instr.att[i], \cf6 \\al\cf3 , instr.al[i], \cf6 \\dt\cf3 , instr.dt[i], \cf6 \\sl\cf3 , instr.sl[i], \cf6 \\rt\cf3 , instr.rt[i] ]);\
	\});\
	\
\
\
}