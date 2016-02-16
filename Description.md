# Music manager

**MP3Joiner**: as a user I want to join my mp3 from an m3u list.

**Acceptance criteria**:
* system asks which m3u file need to be progressed
* system should log out in case of non existant file
* system should dispatch the end of the process
* system should log out the absolute path of the generated mp3 file
* ID3 tag should be ignored only the last mp3 file's ID3 info need to be remained
* generated filename should be:  
  {m3u_name}.mp3
* generated mp3 need to be a joined version of files in m3u
* if he m3u contains a reference to a non existant file the system should write an error message to the console:  
  Missing file: {missing_files_name}  
  procees need to be terminated

--------

**DirectoryScanner**: as a user I want to collect every mp3 file under a given path.

**Acceptance criteria**:
* system asks which folder need to be investigated
* system warns the user if the given folder not exists
* system asks the user if he/she would collect files recursively
* system log out the relative path of mp3 files

--------

**ID3Tag**: as a user I want to get the id3 data from an mp3 file
and I want to be able to modify the id3 tag properties on a given mp3 file.

**Acceptance criteria**:
* system asks the user which mp3 file's he/she would like to see ID3 info
* in case of non existant file the system warns the user
* system log out the ID3 tag info to the console
* system asks the user would he/she modify any ID3 tag property
* collect new values of properties and write it back to the mp3 file

--------

Sorter: as a user I want to be able to sort my mp3 files by the id3 tag properties.

**Acceptance criteria**:
* system asks which property (from a given list) should be the base of sorting:  
  [title, artist, album, year, genre]
* in case of wrong property given the system should warn the user
* system asks the direction of sorting
* system sort a given file list (prepared file list) by the given ID3 property
* system logs out the sorted files' absolute path

--------

tags:
error handling
file handling
Java
OOP
