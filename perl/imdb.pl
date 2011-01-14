#!/usr/bin/perl

use IMDB::Film;
use LWP::Simple;
use IO::File;
use XML::Writer;
use Storable;

$movieWriter;
$tvSeriesWriter;

my $requiredNum = $ARGV[1];

($second, $minute, $hour, $dayOfMonth, $month, $yearOffset, $dayOfWeek, $dayOfYear, $daylightSavings) = localtime();

sub createMovieWriter(){
  my $output = new IO::File(">movieGlossary".$dayOfMonth.$month.$hour.$minute.$second.".xml");
  $movieWriter = new XML::Writer( OUTPUT => $output );
  $movieWriter->xmlDecl( 'UTF-8' );
  $movieWriter->doctype( 'glossary' );
}

sub createTVSeriesWriter(){
  my $output = new IO::File(">tvSeriesGlossary".$dayOfMonth.$month.$hour.$minute.$second.".xml");
  $tvSeriesWriter = new XML::Writer( OUTPUT => $output );
  $tvSeriesWriter->xmlDecl( 'UTF-8' );
  $tvSeriesWriter->doctype( 'glossary' );
}

sub writeDocument {
      ($writer, $film) = @_;
      $writer->startTag('movie');
      $writer->startTag('title');
      $writer->characters($film->title());
      $writer->endTag();
      $writer->startTag('category');
      $writer->characters($film->kind());
      $writer->endTag();
      $writer->startTag('year');
      $writer->characters($film->year());
      $writer->endTag();
      $writer->startTag('rating');
      $writer->characters($film->rating());
      $writer->endTag();
      $writer->startTag('plot');
      $writer->characters($film->full_plot());
      $writer->endTag();
      $writer->endTag('movie');
}
my $movieCount = 1;
my $tvSeriesCount = 1;
my $imdbNumber = $ARGV[0];

createMovieWriter();
createTVSeriesWriter();

$movieWriter->startTag('glossary');
$tvSeriesWriter->startTag('glossary');
$hashFile = "movie.hash";
%movieTraced = %{retrieve($hashFile)};


while($movieCount <= $requiredNum || $tvSeriesCount <= $requiredNum){
  if(not exists $movieTraced{$imdbNumber}){
    $sleep = int(rand($ARGV[2]));
    sleep($sleep);
    my $film = new IMDB::Film(crit => $imdbNumber);
    my $title = $film->title();
    my $kind = $film->kind();
    print "processing $kind '$title'...";

    if($kind eq "movie"){
      if($movieCount > $requiredNum){
	print "reached limition($requiredNum), skip\n";
      }
      else {
	writeDocument($movieWriter, $film);
	print "done, no.$movieCount\n";
	$movieCount++;
	my $file = "movieCovers/$title.jpg";
	my $cover = $film->cover();
	getstore($cover,$file);
	$movieTraced{$imdbNumber} = $title;
	store \%movieTraced, $hashFile;
      }
    }
    elsif($kind eq "tv series"){
      if($tvSeriesCount > $requiredNum){
	print "reached limition($requiredNum), skip\n";
      }
      else{
	writeDocument($tvSeriesWriter, $film);
	print "done, no.$tvSeriesCount\n";
	$tvSeriesCount++;
        my $file = "movieCovers/$title.jpg";
	my $cover = $film->cover();
	getstore($cover,$file);
	$movieTraced{$imdbNumber} = $title;
	store \%movieTraced, $hashFile;
      }
    }
    else {
      print "ratner than movie nor tv series, skip\n";
    }
  }
  else{
    print " $imdbNumber has already fatched, skip\n";
  }
  my $addMinus = int(rand(2));
  my $modifier = int(rand(500));

  if($addMinus == 0){
    $imdbNumber = $imdbNumber + $modifier;
  }
  else{
    $imdbNumber = $imdbNumber - $modifier;
  }
}

$movieWriter->endTag('glossary');
$tvSeriesWriter->endTag('glossary');
$movieWriter->end();
$tvSeriesWriter->end();
print "finished\n";
