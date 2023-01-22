Lower Sorbian voice data for MaryTTS
====================================

Lower Sorbian speech database for TTS voices in [MaryTTS](http://mary.dfki.de/), recorded in 2022 at the [Sorbian Institute](https://www.serbski-institut.de/).

Format
------

The audio data is provided in a single [FLAC](https://xiph.org/flac/) file, recorded at 44.1 kHz sampling frequency with 16 bit per sample.

The textual data is provided in a single [YAML](https://yaml.org/) file.
This file is a list of utterances, each of which contains
- a **prompt** code (file basename),
- the utterance **text**,
- utterance **start** and **end** times (in seconds) in the FLAC file,
- optionally, the phonetic **segments**, each of which has
    - a **lab**el (based on [SAMPA](https://www.phon.ucl.ac.uk/home/sampa/english.htm), `_` denotes silence), and
    - its **dur**ation (in seconds)

The phonetic segmentation has been prepared using the [Kaldi](https://kaldi-asr.org/)-based [Montreal Forced Aligner](https://montrealcorpustools.github.io/Montreal-Forced-Aligner/).

Downloading the data
--------------------

Use the links on the [releases](../../releases) page, or run the `unpackData` Gradle task (see below).

Converting the data
-------------------

For convenience, the utterances for each subset can be be extracted from the YAML and FLAC files using simple commands to run [Gradle](https://gradle.org/) tasks.
After cloning or downloading and unpacking this repository, run `./gradlew tasks` (or `gradlew tasks` on Windows) for details.

### Prerequisites

You will need [Java](https://openjdk.org/) to run the tasks. Extracting the utterances to WAV files also requires [SoX](https://sox.sourceforge.net/) to be installed.

Assembling the data
-------------------

The data processing is delegated to [Gradle](https://gradle.org/) and the [Kaldi MFA](https://github.com/marytts/gradle-marytts-kaldi-mfa-plugin) and [FLAML](https://github.com/m2ci-msp/gradle-flaml-plugin) plugins.

Run `./gradlew unpackData` to only download and extract all data.

Run `./gradlew assemble` to download, extract, and force-align all data, and prepare it for distribution.

Copyright and license
---------------------

Copyright 2022 [Sorbian Institute](https://www.serbski-institut.de/).

![CC-BY-NC-SA-4.0](https://mirrors.creativecommons.org/presskit/buttons/88x31/svg/by-nc-sa.svg)

This work is licensed under a [Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License](https://creativecommons.org/licenses/by-nc-sa/4.0/).
