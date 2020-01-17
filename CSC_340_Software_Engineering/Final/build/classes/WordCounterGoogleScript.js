/**
 * @OnlyCurrentDoc
 *
 * The above comment directs Apps Script to limit the scope of file
 * access for this add-on. It specifies that this add-on will only
 * attempt to read or modify the files in which the add-on is used,
 * and not all of the user's files. The authorization request message
 * presented to users will reflect this limited scope.
 */
 
var freq;
var savedWords = [" the "," I "," my "," his "," him "," her "," hers ",
                  " them "," theirs "," their "," of "," a "," at "," in ",
                 " have "," was "," were "," with "," as "," or "," is "," me ",
                  " but "," but "," which "," so "," on "," it "," not "," am ",
                 " by "," that "," this "," for "," see "," saw "," into ",
                 " be "," did "," to "," an "," and "," he "," she "," had "]      
                 //words to exclude when counting reps in doc

/**
 * Creates a menu entry in the Google Docs UI when the document is opened.
 * This method is only used by the regular add-on, and is never called by
 * the mobile add-on version.
 *
 * @param {object} e The event parameter for a simple onOpen trigger. To
 *     determine which authorization mode (ScriptApp.AuthMode) the trigger is
 *     running in, inspect e.authMode.
 */
function onOpen(e) {
  DocumentApp.getUi().createAddonMenu()
      .addItem('Show Reoccuring Words', 'seperateDocToWords')
      .addToUi();
}

/**
 * Runs when the add-on is installed.
 * This method is only used by the regular add-on, and is never called by
 * the mobile add-on version.
 *
 * @param {object} e The event parameter for a simple onInstall trigger. To
 *     determine which authorization mode (ScriptApp.AuthMode) the trigger is
 *     running in, inspect e.authMode. (In practice, onInstall triggers always
 *     run in AuthMode.FULL, but onOpen triggers may be AuthMode.LIMITED or
 *     AuthMode.NONE.)
 */
function onInstall(e) {
  onOpen(e);
}

/**
 * Opens a sidebar in the document containing the add-on's user interface.
 * This method is only used by the regular add-on, and is never called by
 * the mobile add-on version.
 * NOT CURRENTLY IMPLEMENTED 
 */
/*
function showSidebar() {
  var ui = HtmlService.createHtmlOutputFromFile('Sidebar')
      .setTitle('Editor');
  ui = HtmlService.createHtmlOutput();
  ui.setContent('<b>Hello, world!</b>');
  ui.setContent(myVar);
  DocumentApp.getUi().showSidebar(ui);
}
*/


/**
 * Reads the paragraph and stores every word in freq[], 
 * each with a corresponding frequency counter
 *
 * @param {string} text the paragraph that will be seperated
 * @return {void}
*/
function splitWords(text){
  var words = text.split(" ");
  for (i=0; i<words.length; i++){
    if(freq[words[i]] == undefined){              //if it's not in the map, 
      freq[words[i]] = 1;                         //add it and set freq to 1
    }
    else
      freq[words[i]]++; //else, inc
  }
}

/**
 * Seperates the entire document into paragraphs,
 * and passes each one to splitWords, breaking them
 * down into words.
 *
 * @return {Array.<string>} The selected text.
 */
function seperateDocToWords() {
  //document.getElementById("test").text = "DIFFERENT"
  
  freq = {}; //empty map
  var doc = DocumentApp.getActiveDocument();
  var body = doc.getBody();
  var paragraphs = body.getParagraphs();
  for(var i = 0; i < paragraphs.length; i++){
       var text = paragraphs[i].getText();
       splitWords(text);
  }
  
  sorted = [] //new arr to hold the sorted arr
  
  for( var word in freq ){
    if(freq[word] > 2){                           //if it reoccurs more than twice,
    sorted.push([word, freq[word]]);              //add word & freq
                                                  //sort the arr
    }
  }
  sorted.sort(function(a,b){ return b[1] - a[1]}) //sorts sorted[]
  
  freq = {}  //empty freq 
  for( var i = 0; i < sorted.length; i++ ){
    var arr = sorted[i]
    freq[arr[0]] = arr[1]
  }
  
  //find word and send to func to highlight
  for(var i = 0; i < paragraphs.length; i++){
    var myPara = paragraphs[i];
      for(var word in freq){                      //iterate through the words (by freq)
        if(word != ""  &&  notSavedWord(word)){      
          var loc = myPara.findText(" "+word+" ")
          while (loc != null){
            getTextAndHighlight(loc)
            loc = myPara.findText(" "+word+" ", loc)
          }
        }
      }
  }
  for( var word in freq ){                        //prints word & freq
    Logger.log(word+" " + freq[word])
  }
}

function notSavedWord(word){
  for(var i = 0; i<savedWords.length; i++){
    if( " "+word+" " == savedWords[i] ){
      return false;
    }
  }
  return true;
}


/**
 * Gets the user-selected text and translates it from the origin language to the
 * destination language. The languages are notated by their two-letter short
 * form. For example, English is 'en', and Spanish is 'es'. The origin language
 * may be specified as an empty string to indicate that Google Translate should
 * auto-detect the language.
 *
 * @param {string} origin The two-letter short form for the origin language.
 * @param {string} dest The two-letter short form for the destination language.
 * @param {boolean} savePrefs Whether to save the origin and destination
 *     language preferences.
 * @return {Object} Object containing the original text and the result of the
 *     translation.
 */
function getTextAndHighlight(myLoc) {
  var highlightStyle = {};
  //generate random int in ceratin range and convert to hex
  highlightStyle[DocumentApp.Attribute.FOREGROUND_COLOR] = '#FF0000';
  myLoc.getElement().setAttributes(myLoc.getStartOffset(),myLoc.getEndOffsetInclusive(), highlightStyle);
}


/**
 * Given text, translate it from the origin language to the destination
 * language. The languages are notated by their two-letter short form. For
 * example, English is 'en', and Spanish is 'es'. The origin language may be
 * specified as an empty string to indicate that Google Translate should
 * auto-detect the language.
 *
 * @param {string} text text to translate.
 * @param {string} origin The two-letter short form for the origin language.
 * @param {string} dest The two-letter short form for the destination language.
 * @return {string} The result of the translation, or the original text if
 *     origin and dest languages are the same.
 */
function translateText(text, origin, dest) {
  if (origin === dest) {
    return text;
  }
  return LanguageApp.translate(text, origin, dest);
}

