# Designertutorial #
Currently only available in German

## Einführung ##

Navigation
Der Designer ist nach dem Wizard Prinizp aufgebaut, was bedeutet er ist in mehrere Schritte unterteilt, wobei über die Buttons next und previous zwischen den Schritten gewechselt werden kann. Es ist grundsätzlich jederzeit möglich beliebig hin- und zurück zu navigieren. Zu beachten ist nur dass Änderungen in einem Schritt Änderungen in spä-teren Schritten nach sich ziehen können.


## Programmstart ##

Zu Programmstart erlaubt der Designer entweder das Erstellen einer neuen Szene oder das Bearbeiten einer bereits existierenden. In beiden Fällen ist der Aufbau und Ablauf des Hauptdialogs ident, wobei beim Bearbeiten bereits definierte Objekte in den jeweili-gen Tabs angezeigt werden.

## Sensoren definieren ##

Im ersten Schritt des Designprozesses wird mit den Sensoren verbunden. Dafür muss für jeden JDDAC Server eine URL inkl. Portangabe und der Loginbenutzer (Standard bei JDDAC ist Admin) angegeben werden. Durch den Add Button wird der Server in die Liste hinzugefügt und automatisch verbunden. Eine Statusspalte gibt Informationen über den Verbindungsstatus. Es ist nur möglich zum nächsten Schritt fortzufahren wenn zu allen in der Liste befindlichen Servern eine Verbindung besteht. Mit Reconnect kann versucht werden wieder eine Verbindung herzustellen, mit Remove wird der ausgewählte Server wieder aus der Liste entfernt. Über Show Details oder Doppelklick auf einen Server kön-nen Details eingesehen werden, also alle Sensoren des Server inkl. deren Informationen und Parametern. Wichtig ist dabei z.B. der Datentyp eines jeden Parameters eines Sen-sors. Dieser muss später mit dem Datentyp einer Animation übereinstimmen.

## Hintergrund auswählen ##

Als nächstes wird der Hintergrund der Szene festgelegt. Dieser bestimmt durch seine Größe automatisch die Größe der Darstellung und dient als Koordinatensystem für die später definierten Animationen. Es bestimmt jedoch nicht die notwendige Visualisie-rungslauflösung. Es werden nur Bilddateien des Formats bmp, png, jpeg, gif und tif un-terstützt. Wird eine Bilddatei selektiert, öffnet sich ein Vorschaudialog und zeigt dieses als Thumbnail an. Das Programm erlaubt kein Fortfahren ohne Selektion eines gültigen Hintergrunds.

## Animationen definieren ##

Nun da der Hintergrund festgelegt wurde, können die Animationen für die Szene defi-niert werden. Dafür dient die folgende Registerkarte des Wizards. Links stellt sie eine Liste mit allen bereits definierten Animationen dar. Es ist möglich neue hinzuzufügen, bestehende zu bearbeiten oder wieder zu löschen. Des Weiteren kann eine Animation in der Position verschoben werden. Da Animationen nach ihrer Reihenfolge in der Liste gezeichnet werden (oberste ganz vorne) kann somit festgelegt werden welche Anima-tionen andere überdecken.
Rechts im Bild werden die Eigenschaften der von der Liste selektierten Animation ange-zeigt. Diese Eigenschaften definieren Parameter wie z.B. den Sensor auf den diese Ani-mation reagieren soll oder Schwellwerte ab wann eine Animation reagiert.
Alle Einstellungen müssen gültige Werte besitzen, dies wird beim Klick auf den Save Button unten rechts überprüft. Sollte eine Eingabe nicht korrekt sein wird der Benutzer darüber informiert die entsprechende Einstellung zu ändern.
Durch Klick auf den + Button öffnet sich der Dialog zum Erstellen einer Animation. (sie-he Animation erstellen).
Beim Klick auf den Save Button wird, sollten keine invaliden Einstellungen vorhanden sein, ein Dialog geöffnet wobei der Benutzer den Pfad und Namen der Konfigurationsda-tei angeben kann. Bitte beachten Sie, dass die Konfigurationsdatei mit der Endung ‚.zip’ versehen werden muss. Wurde ein korrekter Name für die Konfigurationsdatei eingege-ben wird die Datei gespeichert und das Programm beendet.

## Animationen erstellen ##

Wurde der + Button geklickt öffnet sich ein neuer Dialog welcher schrittweise durch den Erstellungsprozess einer neuen Animation führt. Schritt eins verlangt die Auswahl ei-nes Animationstyps. Es stehen eine Vielzahl von Animationen zur Auswahl, eine Be-schreibung über deren Funktion wird rechts im Bild angezeigt. Die Animation legt dabei bereits eine Vielzahl an Einstellungen fest, einige können nach Erzeugung in den Anima-tionseinstellungen des Hauptdialogs geändert werden. Es ist immer notwendig einen passenden Sensor für eine Animation auszuwählen.

## Sensoren vor-auswählen ##

Da es möglich ist eine Vielzahl von Sensorservern und daher auch eine Vielzahl von Sen-soren auszuwählen, bietet das Programm eine Vorselektion von Sensoren an. In diesem Schritt sollen alle Sensoren ausgewählt werden die nur für diese Animation von Bedeu-tung sind. Links befindet sich eine Liste mit allen verfügbaren Sensoren, rechts werden alle vorselektierten angezeigt. Durch Selektieren und anschließendes Klicken der But-tons < und > in der Mitte kann jeder Sensor von einer Liste in die andere übertragen werden.
An dieser Stelle sei erwähnt dass jedes Sensorattribut einen bestimmten Datentyp ver-wendet. Es gibt z.B. numerische Sensorwerte wie einen Temperatursensor oder auch einen Sensor der Text aus einer Webseite ausliest. Jede Animation kann nur mit be-stimmten Sensorwerten umgehen, daher kann nicht jeder Sensor für jede Animation verwendet werden. Es kann also vorkommen, dass obwohl ein Sensor vorselektiert wurde, kein Sensorattribut bei den Eigenschaften der Animation auswählbar ist. Die Da-tentypen der Sensoren können im Schritt „Sensoren definieren“ unter Details eingese-hen werden.
Weiters ist es möglich einen Namen für die Animation zu definieren. Dies ermöglicht eine logische Benennung von Animationen und erleichtert die spätere Unterscheidbar-keit in der Animationsliste.

## Positionen der Animationen festlegen ##

In diesem Schritt werden die Positionen und Bilder für die Animation festgelegt. Abhän-gig von der Art der Animation ist es möglich hier entweder einen Punkt, eine Linie oder eine Region zu definieren. Auch mehrere Punkte oder Linien sind möglich. Durch + und – neben der Positionsliste können neue hinzugefügt bzw. bestehende gelöscht werden. Pro Animation ist nur ein Typ von Position möglich, ist also die Position ein Punkt kön-nen nur Punkte hinzugefügt werden.
Unterhalb der Liste werden die Koordination der ausgewählten Position angezeigt. Durch Eingabe einer neuen Koordinate wird diese an die neue Koordinate versetzt. Es ist möglich sowohl negative als auch über die Hintergrundauflösung hinausgehende Werte anzugeben. Das dient dazu Objekte von außen durch eine Animation in die Szene bewegen zu lassen.
Im rechten Teil des Fensters können zur aktuell ausgewählten Position Symbole hinzu-gefügt werden. Symbole sind im Endeffekt Bilddateien, wobei eine Position auch mehre-re Symbole, also Bilder haben kann. Zum Beispiel kann ein Swapper an einem Punkt mehrere Bilder definieren. Abhängig vom Animationstyp kann ein Symbol sogar aus zwei Bildern bestehen. Das ist der Fall wenn sich ein Symbol später in der Visualisierung bewegt und es je nach Orientierung das Bild wechseln soll (z.B. soll ein Flugzeug je nachdem ob es nach links oder rechts fliegt auch ein entsprechendes Bild anzeigen wo es in die richtige Richtung fliegt).

Dieser Teil wäre etwas unübersichtlich und schwer nachvollziehbar ohne irgendeine Ausgabe zu sehen. Daher aktiviert sich automatisch mit dieser Ansicht auch die Vor-schau (sollte sie nach der Hintergrundauswahl geschlossen worden sein). Die aktuell ausgewählte Position wird immer in der Vorschau angezeigt. Dabei wird diese entweder durch eine ähnliche geometrische Form repräsentiert, also
•	Ein x für einen Punkt
•	Eine Linie als Strich mit jeweils einem x an einem Ende
•	Ein Rechteck für eine Region mit einem x im rechten unteren Bereich
Diese Darstellungen dienen jedoch nicht nur der grafischen Überprüfung der korrekten Platzierung sondern können auch zur Positionierung verwendet werden. So kann zum Beispiel ein Punkt durch Klicken in den x Bereich und anschließendes ziehen (drag&drop) verschoben werden. Selbes gilt auch für beide x an den Enden der Linie.
Die Region kann durch Klicken innerhalb der Region (nicht auf das x) auch verschoben werden, durch Klicken in den x Bereich im rechten unteren Rand wird sie in der Größe verändert.
Während einer Drag&Drop Aktion können an den Werten in den Textfeldern unterhalb der Positionsliste die genauen Koordinaten mitverfolgt werden.
Wurde für eine Position ein Symbol ausgewählt ist es auch möglich dieses Symbol (das Bild) anstatt der geometrischen Form anzuzeigen. Dafür muss für eine Position minde-stens ein Symbol ausgewählt werden und die CheckBox „display symbol in preview“ akti-viert werden. Nun wird das ausgewählte Bild an der jeweiligen Position angezeigt und kann auch genauso mittels Drag&Drop verschoben werden.

## Regeln festlegen ##

Wurde eine Animation gewählt welche auf Regeln basiert kann man als vierten Schritt eine beliebige Anzahl an Regeln für diese Animation festlegen. Jede Regel besteht wie-derum aus einer beliebigen Anzahl an Bedingungen und einer Aktion. Während der Vi-sualisierung werden alle Bedingungen einer Regel überprüft, nur wenn alle Bedingun-gen erfüllt sind wird die angegebene Aktion durchgeführt. Regeln untereinander sind oder verknüpft, was bedeutet es wird die Aktion der ersten Regel ausgeführt bei der alle Bedingungen erfüllt sind. Das bedeutet jedoch auch wenn die Bedingungen der ersten Regel immer erfüllt sind kommt keine andere Regel zum Zug.
Eine Bedingung besteht aus drei Teilen. Der linke Teil legt den Parameter eines vordefi-nierten Sensors fest auf dem die Regel angewendet werden soll. Wurde ein Sensorpara-meter ausgewählt erscheinen im mittleren Teil alle Operatoren die auf diesen ange-wandt werden können. Schließlich kann im rechten Teil der Bedingung der Wert ange-geben werden für den der Operand gelten soll. Also wie in der Abbildung unten wird der Sensorparameter AirTemp auf „gleich 0“ überprüft. Natürlich ist es auch möglich beste-hende Bedingungen durch den Delete Button rechts wieder zu entfernen.
Oft ist auch ein bestimmtes Verhalten gewünscht wenn keine der angegebenen Regeln greifen. Dafür ist es möglich eine so genannte DefaultRule zu definieren. Diese Regel hat keine Bedingungen und definiert die auszuführende Aktion wenn keine der anderen Regeln feuert.
Durch anschließendes Klicken des Finish Buttons wird die erstellte Animation in der Animationsliste des Hauptdialogs hinzugefügt.