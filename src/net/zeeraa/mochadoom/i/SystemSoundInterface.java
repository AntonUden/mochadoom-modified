package net.zeeraa.mochadoom.i;

import net.zeeraa.mochadoom.data.sfxinfo_t;


/*
// UNIX hack, to be removed.
#ifdef SNDSERV
#include <stdio.h>
extern FILE* sndserver;
extern char* sndserver_filename;
#endif*/


public interface SystemSoundInterface{


// Init at program start...
public void InitSound();

// ... update sound buffer and audio device at runtime...
public void UpdateSound();
public void SubmitSound();

// ... shut down and relase at program termination.
public void ShutdownSound();


//
//  SFX I/O
//

// Initialize channels?
void SetChannels();

// Get raw data lump index for sound descriptor.
public int GetSfxLumpNum (sfxinfo_t sfxinfo );


// Starts a sound in a particular sound channel.
public int
StartSound
( int		id,
  int		vol,
  int		sep,
  int		pitch,
  int		priority );


// Stops a sound channel.
public void StopSound(int handle);

// Called by S_*() functions
//  to see if a channel is still playing.
// Returns 0 if no longer playing, 1 if playing.
public boolean SoundIsPlaying(int handle);

// Updates the volume, separation,
//  and pitch of a sound channel.
public void
UpdateSoundParams
( int		handle,
  int		vol,
  int		sep,
  int		pitch );


//
//  MUSIC I/O
//
public void InitMusic();
public void ShutdownMusic();
// Volume.
public void SetMusicVolume(int volume);
// PAUSE game handling.
public void PauseSong(int handle);
public void ResumeSong(int handle);
// Registers a song handle to song data.
public int RegisterSong(byte[] data);
// Called by anything that wishes to start music.
//  plays a song, and when the song is done,
//  starts playing it again in an endless loop.
// Horrible thing to do, considering.
public void
PlaySong
( int		handle,
  int		looping );
// Stops a song over 3 seconds.
public void StopSong(int handle);
// See above (register), then think backwards
public void UnRegisterSong(int handle);
}