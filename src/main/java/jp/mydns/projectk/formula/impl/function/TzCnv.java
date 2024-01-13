/*
 * Copyright (c) 2024, Project-K
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package jp.mydns.projectk.formula.impl.function;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import jp.mydns.projectk.formula.FormulaExecutionException;
import jp.mydns.projectk.formula.impl.AbstractFunction;
import jp.mydns.projectk.formula.impl.ArgdefImpl;
import jp.mydns.projectk.formula.impl.ArgumentSchemeImpl;

/**
 * Formula function to change the time zone of a datetime.
 *
 * @author riru
 * @version 1.0.0
 * @since 1.0.0
 */
public class TzCnv extends AbstractFunction {

    /**
     * Run this function.
     *
     * @param args valid number arguments
     * <table border="1"><caption>Arguments definition</caption>
     * <thead><tr><th>#</th><th>name</th><th>type</th><th>description</th></tr></thead>
     * <tbody>
     * <tr><td>1</td><td>DateTime</td><td>LocalDateTime</td><td>Source datetime. It format is
     * {@code uuuu-MM-ddTHH:mm:ss}</td></tr>
     * <tr><td>2</td><td>SourceZoneId</td><td>ZoneId</td><td>ZoneId of source datetime.</td></tr>
     * <tr><td>3</td><td>ResultZoneId</td><td>ZoneId</td><td>ZoneId of result datetime.</td></tr>
     * </tbody></table>
     * <br>
     * <table border="1"><caption>ZoneId list available as of January 11, 2024</caption>
     * <thead><tr><th>ZoneId</th><th>Description</th></tr></thead>
     * <tbody>
     * <tr><td>Africa/Abidjan</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Africa/Accra</td><td>Ghana Mean Time</td></tr>
     * <tr><td>Africa/Addis_Ababa</td><td>Eastern Africa Time</td></tr>
     * <tr><td>Africa/Algiers</td><td>Central European Time</td></tr>
     * <tr><td>Africa/Asmara</td><td>Eastern Africa Time</td></tr>
     * <tr><td>Africa/Asmera</td><td>Eastern Africa Time</td></tr>
     * <tr><td>Africa/Bamako</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Africa/Bangui</td><td>West Africa Time</td></tr>
     * <tr><td>Africa/Banjul</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Africa/Bissau</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Africa/Blantyre</td><td>Central Africa Time</td></tr>
     * <tr><td>Africa/Brazzaville</td><td>West Africa Time</td></tr>
     * <tr><td>Africa/Bujumbura</td><td>Central Africa Time</td></tr>
     * <tr><td>Africa/Cairo</td><td>Eastern European Time</td></tr>
     * <tr><td>Africa/Casablanca</td><td>Western European Time</td></tr>
     * <tr><td>Africa/Ceuta</td><td>Central European Time</td></tr>
     * <tr><td>Africa/Conakry</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Africa/Dakar</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Africa/Dar_es_Salaam</td><td>Eastern Africa Time</td></tr>
     * <tr><td>Africa/Djibouti</td><td>Eastern Africa Time</td></tr>
     * <tr><td>Africa/Douala</td><td>West Africa Time</td></tr>
     * <tr><td>Africa/El_Aaiun</td><td>Western European Time</td></tr>
     * <tr><td>Africa/Freetown</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Africa/Gaborone</td><td>Central Africa Time</td></tr>
     * <tr><td>Africa/Harare</td><td>Central Africa Time</td></tr>
     * <tr><td>Africa/Johannesburg</td><td>South Africa Time</td></tr>
     * <tr><td>Africa/Juba</td><td>Central Africa Time</td></tr>
     * <tr><td>Africa/Kampala</td><td>Eastern Africa Time</td></tr>
     * <tr><td>Africa/Khartoum</td><td>Central Africa Time</td></tr>
     * <tr><td>Africa/Kigali</td><td>Central Africa Time</td></tr>
     * <tr><td>Africa/Kinshasa</td><td>West Africa Time</td></tr>
     * <tr><td>Africa/Lagos</td><td>West Africa Time</td></tr>
     * <tr><td>Africa/Libreville</td><td>West Africa Time</td></tr>
     * <tr><td>Africa/Lome</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Africa/Luanda</td><td>West Africa Time</td></tr>
     * <tr><td>Africa/Lubumbashi</td><td>Central Africa Time</td></tr>
     * <tr><td>Africa/Lusaka</td><td>Central Africa Time</td></tr>
     * <tr><td>Africa/Malabo</td><td>West Africa Time</td></tr>
     * <tr><td>Africa/Maputo</td><td>Central Africa Time</td></tr>
     * <tr><td>Africa/Maseru</td><td>South Africa Time</td></tr>
     * <tr><td>Africa/Mbabane</td><td>South Africa Time</td></tr>
     * <tr><td>Africa/Mogadishu</td><td>Eastern Africa Time</td></tr>
     * <tr><td>Africa/Monrovia</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Africa/Nairobi</td><td>Eastern Africa Time</td></tr>
     * <tr><td>Africa/Ndjamena</td><td>West Africa Time</td></tr>
     * <tr><td>Africa/Niamey</td><td>West Africa Time</td></tr>
     * <tr><td>Africa/Nouakchott</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Africa/Ouagadougou</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Africa/Porto-Novo</td><td>West Africa Time</td></tr>
     * <tr><td>Africa/Sao_Tome</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Africa/Timbuktu</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Africa/Tripoli</td><td>Eastern European Time</td></tr>
     * <tr><td>Africa/Tunis</td><td>Central European Time</td></tr>
     * <tr><td>Africa/Windhoek</td><td>Central African Time</td></tr>
     * <tr><td>America/Adak</td><td>Hawaii-Aleutian Time</td></tr>
     * <tr><td>America/Anchorage</td><td>Alaska Time</td></tr>
     * <tr><td>America/Anguilla</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Antigua</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Araguaina</td><td>Brasilia Time</td></tr>
     * <tr><td>America/Argentina/Buenos_Aires</td><td>Argentina Time</td></tr>
     * <tr><td>America/Argentina/Catamarca</td><td>Argentina Time</td></tr>
     * <tr><td>America/Argentina/ComodRivadavia</td><td>Argentina Time</td></tr>
     * <tr><td>America/Argentina/Cordoba</td><td>Argentina Time</td></tr>
     * <tr><td>America/Argentina/Jujuy</td><td>Argentina Time</td></tr>
     * <tr><td>America/Argentina/La_Rioja</td><td>Argentina Time</td></tr>
     * <tr><td>America/Argentina/Mendoza</td><td>Argentina Time</td></tr>
     * <tr><td>America/Argentina/Rio_Gallegos</td><td>Argentina Time</td></tr>
     * <tr><td>America/Argentina/Salta</td><td>Argentina Time</td></tr>
     * <tr><td>America/Argentina/San_Juan</td><td>Argentina Time</td></tr>
     * <tr><td>America/Argentina/San_Luis</td><td>Argentina Time</td></tr>
     * <tr><td>America/Argentina/Tucuman</td><td>Argentina Time</td></tr>
     * <tr><td>America/Argentina/Ushuaia</td><td>Argentina Time</td></tr>
     * <tr><td>America/Aruba</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Asuncion</td><td>Paraguay Time</td></tr>
     * <tr><td>America/Atikokan</td><td>Eastern Time</td></tr>
     * <tr><td>America/Atka</td><td>Hawaii-Aleutian Time</td></tr>
     * <tr><td>America/Bahia</td><td>Brasilia Time</td></tr>
     * <tr><td>America/Bahia_Banderas</td><td>Central Time</td></tr>
     * <tr><td>America/Barbados</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Belem</td><td>Brasilia Time</td></tr>
     * <tr><td>America/Belize</td><td>Central Time</td></tr>
     * <tr><td>America/Blanc-Sablon</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Boa_Vista</td><td>Amazon Time</td></tr>
     * <tr><td>America/Bogota</td><td>Colombia Time</td></tr>
     * <tr><td>America/Boise</td><td>Mountain Time</td></tr>
     * <tr><td>America/Buenos_Aires</td><td>Argentina Time</td></tr>
     * <tr><td>America/Cambridge_Bay</td><td>Mountain Time</td></tr>
     * <tr><td>America/Campo_Grande</td><td>Amazon Time</td></tr>
     * <tr><td>America/Cancun</td><td>Eastern Time</td></tr>
     * <tr><td>America/Caracas</td><td>Venezuela Time</td></tr>
     * <tr><td>America/Catamarca</td><td>Argentina Time</td></tr>
     * <tr><td>America/Cayenne</td><td>French Guiana Time</td></tr>
     * <tr><td>America/Cayman</td><td>Eastern Time</td></tr>
     * <tr><td>America/Chicago</td><td>Central Time</td></tr>
     * <tr><td>America/Chihuahua</td><td>Central Time</td></tr>
     * <tr><td>America/Ciudad_Juarez</td><td>Mountain Time</td></tr>
     * <tr><td>America/Coral_Harbour</td><td>Eastern Time</td></tr>
     * <tr><td>America/Cordoba</td><td>Argentina Time</td></tr>
     * <tr><td>America/Costa_Rica</td><td>Central Time</td></tr>
     * <tr><td>America/Creston</td><td>Mountain Time</td></tr>
     * <tr><td>America/Cuiaba</td><td>Amazon Time</td></tr>
     * <tr><td>America/Curacao</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Danmarkshavn</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>America/Dawson</td><td>Mountain Time</td></tr>
     * <tr><td>America/Dawson_Creek</td><td>Mountain Time</td></tr>
     * <tr><td>America/Denver</td><td>Mountain Time</td></tr>
     * <tr><td>America/Detroit</td><td>Eastern Time</td></tr>
     * <tr><td>America/Dominica</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Edmonton</td><td>Mountain Time</td></tr>
     * <tr><td>America/Eirunepe</td><td>Acre Time</td></tr>
     * <tr><td>America/El_Salvador</td><td>Central Time</td></tr>
     * <tr><td>America/Ensenada</td><td>Pacific Time</td></tr>
     * <tr><td>America/Fort_Nelson</td><td>Mountain Time</td></tr>
     * <tr><td>America/Fort_Wayne</td><td>Eastern Time</td></tr>
     * <tr><td>America/Fortaleza</td><td>Brasilia Time</td></tr>
     * <tr><td>America/Glace_Bay</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Godthab</td><td>West Greenland Time</td></tr>
     * <tr><td>America/Goose_Bay</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Grand_Turk</td><td>Eastern Time</td></tr>
     * <tr><td>America/Grenada</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Guadeloupe</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Guatemala</td><td>Central Time</td></tr>
     * <tr><td>America/Guayaquil</td><td>Ecuador Time</td></tr>
     * <tr><td>America/Guyana</td><td>Guyana Time</td></tr>
     * <tr><td>America/Halifax</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Havana</td><td>Cuba Time</td></tr>
     * <tr><td>America/Hermosillo</td><td>Mexican Pacific Time</td></tr>
     * <tr><td>America/Indiana/Indianapolis</td><td>Eastern Time</td></tr>
     * <tr><td>America/Indiana/Knox</td><td>Central Time</td></tr>
     * <tr><td>America/Indiana/Marengo</td><td>Eastern Time</td></tr>
     * <tr><td>America/Indiana/Petersburg</td><td>Eastern Time</td></tr>
     * <tr><td>America/Indiana/Tell_City</td><td>Central Time</td></tr>
     * <tr><td>America/Indiana/Vevay</td><td>Eastern Time</td></tr>
     * <tr><td>America/Indiana/Vincennes</td><td>Eastern Time</td></tr>
     * <tr><td>America/Indiana/Winamac</td><td>Eastern Time</td></tr>
     * <tr><td>America/Indianapolis</td><td>Eastern Time</td></tr>
     * <tr><td>America/Inuvik</td><td>Mountain Time</td></tr>
     * <tr><td>America/Iqaluit</td><td>Eastern Time</td></tr>
     * <tr><td>America/Jamaica</td><td>Eastern Time</td></tr>
     * <tr><td>America/Jujuy</td><td>Argentina Time</td></tr>
     * <tr><td>America/Juneau</td><td>Alaska Time</td></tr>
     * <tr><td>America/Kentucky/Louisville</td><td>Eastern Time</td></tr>
     * <tr><td>America/Kentucky/Monticello</td><td>Eastern Time</td></tr>
     * <tr><td>America/Knox_IN</td><td>Central Time</td></tr>
     * <tr><td>America/Kralendijk</td><td>Atlantic Time</td></tr>
     * <tr><td>America/La_Paz</td><td>Bolivia Time</td></tr>
     * <tr><td>America/Lima</td><td>Peru Time</td></tr>
     * <tr><td>America/Los_Angeles</td><td>Pacific Time</td></tr>
     * <tr><td>America/Louisville</td><td>Eastern Time</td></tr>
     * <tr><td>America/Lower_Princes</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Maceio</td><td>Brasilia Time</td></tr>
     * <tr><td>America/Managua</td><td>Central Time</td></tr>
     * <tr><td>America/Manaus</td><td>Amazon Time</td></tr>
     * <tr><td>America/Marigot</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Martinique</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Matamoros</td><td>Central Time</td></tr>
     * <tr><td>America/Mazatlan</td><td>Mexican Pacific Time</td></tr>
     * <tr><td>America/Mendoza</td><td>Argentina Time</td></tr>
     * <tr><td>America/Menominee</td><td>Central Time</td></tr>
     * <tr><td>America/Merida</td><td>Central Time</td></tr>
     * <tr><td>America/Metlakatla</td><td>Alaska Time</td></tr>
     * <tr><td>America/Mexico_City</td><td>Central Time</td></tr>
     * <tr><td>America/Miquelon</td><td>St. Pierre &amp; Miquelon Time</td></tr>
     * <tr><td>America/Moncton</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Monterrey</td><td>Central Time</td></tr>
     * <tr><td>America/Montevideo</td><td>Uruguay Time</td></tr>
     * <tr><td>America/Montreal</td><td>Eastern Time</td></tr>
     * <tr><td>America/Montserrat</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Nassau</td><td>Eastern Time</td></tr>
     * <tr><td>America/New_York</td><td>Eastern Time</td></tr>
     * <tr><td>America/Nipigon</td><td>Eastern Time</td></tr>
     * <tr><td>America/Nome</td><td>Alaska Time</td></tr>
     * <tr><td>America/Noronha</td><td>Fernando de Noronha Time</td></tr>
     * <tr><td>America/North_Dakota/Beulah</td><td>Central Time</td></tr>
     * <tr><td>America/North_Dakota/Center</td><td>Central Time</td></tr>
     * <tr><td>America/North_Dakota/New_Salem</td><td>Central Time</td></tr>
     * <tr><td>America/Nuuk</td><td>West Greenland Time</td></tr>
     * <tr><td>America/Ojinaga</td><td>Central Time</td></tr>
     * <tr><td>America/Panama</td><td>Eastern Time</td></tr>
     * <tr><td>America/Pangnirtung</td><td>Eastern Time</td></tr>
     * <tr><td>America/Paramaribo</td><td>Suriname Time</td></tr>
     * <tr><td>America/Phoenix</td><td>Mountain Time</td></tr>
     * <tr><td>America/Port-au-Prince</td><td>Eastern Time</td></tr>
     * <tr><td>America/Port_of_Spain</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Porto_Acre</td><td>Acre Time</td></tr>
     * <tr><td>America/Porto_Velho</td><td>Amazon Time</td></tr>
     * <tr><td>America/Puerto_Rico</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Punta_Arenas</td><td>Punta Arenas Time</td></tr>
     * <tr><td>America/Rainy_River</td><td>Central Time</td></tr>
     * <tr><td>America/Rankin_Inlet</td><td>Central Time</td></tr>
     * <tr><td>America/Recife</td><td>Brasilia Time</td></tr>
     * <tr><td>America/Regina</td><td>Central Time</td></tr>
     * <tr><td>America/Resolute</td><td>Central Time</td></tr>
     * <tr><td>America/Rio_Branco</td><td>Acre Time</td></tr>
     * <tr><td>America/Rosario</td><td>Argentina Time</td></tr>
     * <tr><td>America/Santa_Isabel</td><td>Northwest Mexico Time</td></tr>
     * <tr><td>America/Santarem</td><td>Brasilia Time</td></tr>
     * <tr><td>America/Santiago</td><td>Chile Time</td></tr>
     * <tr><td>America/Santo_Domingo</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Sao_Paulo</td><td>Brasilia Time</td></tr>
     * <tr><td>America/Scoresbysund</td><td>East Greenland Time</td></tr>
     * <tr><td>America/Shiprock</td><td>Mountain Time</td></tr>
     * <tr><td>America/Sitka</td><td>Alaska Time</td></tr>
     * <tr><td>America/St_Barthelemy</td><td>Atlantic Time</td></tr>
     * <tr><td>America/St_Johns</td><td>Newfoundland Time</td></tr>
     * <tr><td>America/St_Kitts</td><td>Atlantic Time</td></tr>
     * <tr><td>America/St_Lucia</td><td>Atlantic Time</td></tr>
     * <tr><td>America/St_Thomas</td><td>Atlantic Time</td></tr>
     * <tr><td>America/St_Vincent</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Swift_Current</td><td>Central Time</td></tr>
     * <tr><td>America/Tegucigalpa</td><td>Central Time</td></tr>
     * <tr><td>America/Thule</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Thunder_Bay</td><td>Eastern Time</td></tr>
     * <tr><td>America/Tijuana</td><td>Pacific Time</td></tr>
     * <tr><td>America/Toronto</td><td>Eastern Time</td></tr>
     * <tr><td>America/Tortola</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Vancouver</td><td>Pacific Time</td></tr>
     * <tr><td>America/Virgin</td><td>Atlantic Time</td></tr>
     * <tr><td>America/Whitehorse</td><td>Mountain Time</td></tr>
     * <tr><td>America/Winnipeg</td><td>Central Time</td></tr>
     * <tr><td>America/Yakutat</td><td>Alaska Time</td></tr>
     * <tr><td>America/Yellowknife</td><td>Mountain Time</td></tr>
     * <tr><td>Antarctica/Casey</td><td>Australian Western Time</td></tr>
     * <tr><td>Antarctica/Davis</td><td>Davis Time</td></tr>
     * <tr><td>Antarctica/DumontDUrville</td><td>Dumont-d'Urville Time</td></tr>
     * <tr><td>Antarctica/Macquarie</td><td>Eastern Australia Time</td></tr>
     * <tr><td>Antarctica/Mawson</td><td>Mawson Time</td></tr>
     * <tr><td>Antarctica/McMurdo</td><td>New Zealand Time</td></tr>
     * <tr><td>Antarctica/Palmer</td><td>Chile Time</td></tr>
     * <tr><td>Antarctica/Rothera</td><td>Rothera Time</td></tr>
     * <tr><td>Antarctica/South_Pole</td><td>New Zealand Time</td></tr>
     * <tr><td>Antarctica/Syowa</td><td>Syowa Time</td></tr>
     * <tr><td>Antarctica/Troll</td><td>Troll Time</td></tr>
     * <tr><td>Antarctica/Vostok</td><td>Vostok Time</td></tr>
     * <tr><td>Arctic/Longyearbyen</td><td>Central European Time</td></tr>
     * <tr><td>Asia/Aden</td><td>Arabian Time</td></tr>
     * <tr><td>Asia/Almaty</td><td>Alma-Ata Time</td></tr>
     * <tr><td>Asia/Amman</td><td>Eastern European Time</td></tr>
     * <tr><td>Asia/Anadyr</td><td>Anadyr Time</td></tr>
     * <tr><td>Asia/Aqtau</td><td>Aqtau Time</td></tr>
     * <tr><td>Asia/Aqtobe</td><td>Aqtobe Time</td></tr>
     * <tr><td>Asia/Ashgabat</td><td>Turkmenistan Time</td></tr>
     * <tr><td>Asia/Ashkhabad</td><td>Turkmenistan Time</td></tr>
     * <tr><td>Asia/Atyrau</td><td>Atyrau Time</td></tr>
     * <tr><td>Asia/Baghdad</td><td>Arabian Time</td></tr>
     * <tr><td>Asia/Bahrain</td><td>Arabian Time</td></tr>
     * <tr><td>Asia/Baku</td><td>Azerbaijan Time</td></tr>
     * <tr><td>Asia/Bangkok</td><td>Indochina Time</td></tr>
     * <tr><td>Asia/Barnaul</td><td>Barnaul Time</td></tr>
     * <tr><td>Asia/Beirut</td><td>Eastern European Time</td></tr>
     * <tr><td>Asia/Bishkek</td><td>Kirgizstan Time</td></tr>
     * <tr><td>Asia/Brunei</td><td>Brunei Time</td></tr>
     * <tr><td>Asia/Calcutta</td><td>India Time</td></tr>
     * <tr><td>Asia/Chita</td><td>Yakutsk Time</td></tr>
     * <tr><td>Asia/Choibalsan</td><td>Ulaanbaatar Time</td></tr>
     * <tr><td>Asia/Chongqing</td><td>China Time</td></tr>
     * <tr><td>Asia/Chungking</td><td>China Time</td></tr>
     * <tr><td>Asia/Colombo</td><td>India Time</td></tr>
     * <tr><td>Asia/Dacca</td><td>Bangladesh Time</td></tr>
     * <tr><td>Asia/Damascus</td><td>Eastern European Time</td></tr>
     * <tr><td>Asia/Dhaka</td><td>Bangladesh Time</td></tr>
     * <tr><td>Asia/Dili</td><td>Timor-Leste Time</td></tr>
     * <tr><td>Asia/Dubai</td><td>Gulf Time</td></tr>
     * <tr><td>Asia/Dushanbe</td><td>Tajikistan Time</td></tr>
     * <tr><td>Asia/Famagusta</td><td>Eastern European Time</td></tr>
     * <tr><td>Asia/Gaza</td><td>Eastern European Time</td></tr>
     * <tr><td>Asia/Harbin</td><td>China Time</td></tr>
     * <tr><td>Asia/Hebron</td><td>Eastern European Time</td></tr>
     * <tr><td>Asia/Ho_Chi_Minh</td><td>Indochina Time</td></tr>
     * <tr><td>Asia/Hong_Kong</td><td>Hong Kong Time</td></tr>
     * <tr><td>Asia/Hovd</td><td>Hovd Time</td></tr>
     * <tr><td>Asia/Irkutsk</td><td>Irkutsk Time</td></tr>
     * <tr><td>Asia/Istanbul</td><td>Turkey Time</td></tr>
     * <tr><td>Asia/Jakarta</td><td>West Indonesia Time</td></tr>
     * <tr><td>Asia/Jayapura</td><td>East Indonesia Time</td></tr>
     * <tr><td>Asia/Jerusalem</td><td>Israel Time</td></tr>
     * <tr><td>Asia/Kabul</td><td>Afghanistan Time</td></tr>
     * <tr><td>Asia/Kamchatka</td><td>Petropavlovsk-Kamchatski Time</td></tr>
     * <tr><td>Asia/Karachi</td><td>Pakistan Time</td></tr>
     * <tr><td>Asia/Kashgar</td><td>Xinjiang Time</td></tr>
     * <tr><td>Asia/Kathmandu</td><td>Nepal Time</td></tr>
     * <tr><td>Asia/Katmandu</td><td>Nepal Time</td></tr>
     * <tr><td>Asia/Khandyga</td><td>Yakutsk Time</td></tr>
     * <tr><td>Asia/Kolkata</td><td>India Time</td></tr>
     * <tr><td>Asia/Krasnoyarsk</td><td>Krasnoyarsk Time</td></tr>
     * <tr><td>Asia/Kuala_Lumpur</td><td>Malaysia Time</td></tr>
     * <tr><td>Asia/Kuching</td><td>Malaysia Time</td></tr>
     * <tr><td>Asia/Kuwait</td><td>Arabian Time</td></tr>
     * <tr><td>Asia/Macao</td><td>China Time</td></tr>
     * <tr><td>Asia/Macau</td><td>China Time</td></tr>
     * <tr><td>Asia/Magadan</td><td>Magadan Time</td></tr>
     * <tr><td>Asia/Makassar</td><td>Central Indonesia Time</td></tr>
     * <tr><td>Asia/Manila</td><td>Philippine Time</td></tr>
     * <tr><td>Asia/Muscat</td><td>Gulf Time</td></tr>
     * <tr><td>Asia/Nicosia</td><td>Eastern European Time</td></tr>
     * <tr><td>Asia/Novokuznetsk</td><td>Krasnoyarsk Time</td></tr>
     * <tr><td>Asia/Novosibirsk</td><td>Novosibirsk Time</td></tr>
     * <tr><td>Asia/Omsk</td><td>Omsk Time</td></tr>
     * <tr><td>Asia/Oral</td><td>Oral Time</td></tr>
     * <tr><td>Asia/Phnom_Penh</td><td>Indochina Time</td></tr>
     * <tr><td>Asia/Pontianak</td><td>West Indonesia Time</td></tr>
     * <tr><td>Asia/Pyongyang</td><td>Korean Time</td></tr>
     * <tr><td>Asia/Qatar</td><td>Arabian Time</td></tr>
     * <tr><td>Asia/Qostanay</td><td>Kostanay Time</td></tr>
     * <tr><td>Asia/Qyzylorda</td><td>Qyzylorda Time</td></tr>
     * <tr><td>Asia/Rangoon</td><td>Myanmar Time</td></tr>
     * <tr><td>Asia/Riyadh</td><td>Arabian Time</td></tr>
     * <tr><td>Asia/Saigon</td><td>Indochina Time</td></tr>
     * <tr><td>Asia/Sakhalin</td><td>Sakhalin Time</td></tr>
     * <tr><td>Asia/Samarkand</td><td>Uzbekistan Time</td></tr>
     * <tr><td>Asia/Seoul</td><td>Korean Time</td></tr>
     * <tr><td>Asia/Shanghai</td><td>China Time</td></tr>
     * <tr><td>Asia/Singapore</td><td>Singapore Time</td></tr>
     * <tr><td>Asia/Srednekolymsk</td><td>Srednekolymsk Time</td></tr>
     * <tr><td>Asia/Taipei</td><td>Taipei Time</td></tr>
     * <tr><td>Asia/Tashkent</td><td>Uzbekistan Time</td></tr>
     * <tr><td>Asia/Tbilisi</td><td>Georgia Time</td></tr>
     * <tr><td>Asia/Tehran</td><td>Iran Time</td></tr>
     * <tr><td>Asia/Tel_Aviv</td><td>Israel Time</td></tr>
     * <tr><td>Asia/Thimbu</td><td>Bhutan Time</td></tr>
     * <tr><td>Asia/Thimphu</td><td>Bhutan Time</td></tr>
     * <tr><td>Asia/Tokyo</td><td>Japan Time</td></tr>
     * <tr><td>Asia/Tomsk</td><td>Tomsk Time</td></tr>
     * <tr><td>Asia/Ujung_Pandang</td><td>Central Indonesia Time</td></tr>
     * <tr><td>Asia/Ulaanbaatar</td><td>Ulaanbaatar Time</td></tr>
     * <tr><td>Asia/Ulan_Bator</td><td>Ulaanbaatar Time</td></tr>
     * <tr><td>Asia/Urumqi</td><td>Xinjiang Time</td></tr>
     * <tr><td>Asia/Ust-Nera</td><td>Vladivostok Time</td></tr>
     * <tr><td>Asia/Vientiane</td><td>Indochina Time</td></tr>
     * <tr><td>Asia/Vladivostok</td><td>Vladivostok Time</td></tr>
     * <tr><td>Asia/Yakutsk</td><td>Yakutsk Time</td></tr>
     * <tr><td>Asia/Yangon</td><td>Myanmar Time</td></tr>
     * <tr><td>Asia/Yekaterinburg</td><td>Yekaterinburg Time</td></tr>
     * <tr><td>Asia/Yerevan</td><td>Armenia Time</td></tr>
     * <tr><td>Atlantic/Azores</td><td>Azores Time</td></tr>
     * <tr><td>Atlantic/Bermuda</td><td>Atlantic Time</td></tr>
     * <tr><td>Atlantic/Canary</td><td>Western European Time</td></tr>
     * <tr><td>Atlantic/Cape_Verde</td><td>Cape Verde Time</td></tr>
     * <tr><td>Atlantic/Faeroe</td><td>Western European Time</td></tr>
     * <tr><td>Atlantic/Faroe</td><td>Western European Time</td></tr>
     * <tr><td>Atlantic/Jan_Mayen</td><td>Central European Time</td></tr>
     * <tr><td>Atlantic/Madeira</td><td>Western European Time</td></tr>
     * <tr><td>Atlantic/Reykjavik</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Atlantic/South_Georgia</td><td>South Georgia Time</td></tr>
     * <tr><td>Atlantic/St_Helena</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Atlantic/Stanley</td><td>Falkland Islands Time</td></tr>
     * <tr><td>Australia/ACT</td><td>Eastern Australia Time</td></tr>
     * <tr><td>Australia/Adelaide</td><td>Central Australia Time</td></tr>
     * <tr><td>Australia/Brisbane</td><td>Eastern Australia Time</td></tr>
     * <tr><td>Australia/Broken_Hill</td><td>Central Australia Time</td></tr>
     * <tr><td>Australia/Canberra</td><td>Eastern Australia Time</td></tr>
     * <tr><td>Australia/Currie</td><td>Eastern Australia Time</td></tr>
     * <tr><td>Australia/Darwin</td><td>Central Australia Time</td></tr>
     * <tr><td>Australia/Eucla</td><td>Australian Central Western Time</td></tr>
     * <tr><td>Australia/Hobart</td><td>Eastern Australia Time</td></tr>
     * <tr><td>Australia/LHI</td><td>Lord Howe Time</td></tr>
     * <tr><td>Australia/Lindeman</td><td>Eastern Australia Time</td></tr>
     * <tr><td>Australia/Lord_Howe</td><td>Lord Howe Time</td></tr>
     * <tr><td>Australia/Melbourne</td><td>Eastern Australia Time</td></tr>
     * <tr><td>Australia/NSW</td><td>Eastern Australia Time</td></tr>
     * <tr><td>Australia/North</td><td>Central Australia Time</td></tr>
     * <tr><td>Australia/Perth</td><td>Western Australia Time</td></tr>
     * <tr><td>Australia/Queensland</td><td>Eastern Australia Time</td></tr>
     * <tr><td>Australia/South</td><td>Central Australia Time</td></tr>
     * <tr><td>Australia/Sydney</td><td>Eastern Australia Time</td></tr>
     * <tr><td>Australia/Tasmania</td><td>Eastern Australia Time</td></tr>
     * <tr><td>Australia/Victoria</td><td>Eastern Australia Time</td></tr>
     * <tr><td>Australia/West</td><td>Western Australia Time</td></tr>
     * <tr><td>Australia/Yancowinna</td><td>Central Australia Time</td></tr>
     * <tr><td>Brazil/Acre</td><td>Acre Time</td></tr>
     * <tr><td>Brazil/DeNoronha</td><td>Fernando de Noronha Time</td></tr>
     * <tr><td>Brazil/East</td><td>Brasilia Time</td></tr>
     * <tr><td>Brazil/West</td><td>Amazon Time</td></tr>
     * <tr><td>CET</td><td>Central European Time</td></tr>
     * <tr><td>CST6CDT</td><td>Central Time</td></tr>
     * <tr><td>Canada/Atlantic</td><td>Atlantic Time</td></tr>
     * <tr><td>Canada/Central</td><td>Central Time</td></tr>
     * <tr><td>Canada/Eastern</td><td>Eastern Time</td></tr>
     * <tr><td>Canada/Mountain</td><td>Mountain Time</td></tr>
     * <tr><td>Canada/Newfoundland</td><td>Newfoundland Time</td></tr>
     * <tr><td>Canada/Pacific</td><td>Pacific Time</td></tr>
     * <tr><td>Canada/Saskatchewan</td><td>Central Time</td></tr>
     * <tr><td>Canada/Yukon</td><td>Mountain Time</td></tr>
     * <tr><td>Chile/Continental</td><td>Chile Time</td></tr>
     * <tr><td>Chile/EasterIsland</td><td>Easter Island Time</td></tr>
     * <tr><td>Cuba</td><td>Cuba Time</td></tr>
     * <tr><td>EET</td><td>Eastern European Time</td></tr>
     * <tr><td>EST5EDT</td><td>Eastern Time</td></tr>
     * <tr><td>Egypt</td><td>Eastern European Time</td></tr>
     * <tr><td>Eire</td><td>Irish Time</td></tr>
     * <tr><td>Etc/GMT</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Etc/GMT+0</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Etc/GMT+1</td><td>GMT-01:00</td></tr>
     * <tr><td>Etc/GMT+10</td><td>GMT-10:00</td></tr>
     * <tr><td>Etc/GMT+11</td><td>GMT-11:00</td></tr>
     * <tr><td>Etc/GMT+12</td><td>GMT-12:00</td></tr>
     * <tr><td>Etc/GMT+2</td><td>GMT-02:00</td></tr>
     * <tr><td>Etc/GMT+3</td><td>GMT-03:00</td></tr>
     * <tr><td>Etc/GMT+4</td><td>GMT-04:00</td></tr>
     * <tr><td>Etc/GMT+5</td><td>GMT-05:00</td></tr>
     * <tr><td>Etc/GMT+6</td><td>GMT-06:00</td></tr>
     * <tr><td>Etc/GMT+7</td><td>GMT-07:00</td></tr>
     * <tr><td>Etc/GMT+8</td><td>GMT-08:00</td></tr>
     * <tr><td>Etc/GMT+9</td><td>GMT-09:00</td></tr>
     * <tr><td>Etc/GMT-0</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Etc/GMT-1</td><td>GMT+01:00</td></tr>
     * <tr><td>Etc/GMT-10</td><td>GMT+10:00</td></tr>
     * <tr><td>Etc/GMT-11</td><td>GMT+11:00</td></tr>
     * <tr><td>Etc/GMT-12</td><td>GMT+12:00</td></tr>
     * <tr><td>Etc/GMT-13</td><td>GMT+13:00</td></tr>
     * <tr><td>Etc/GMT-14</td><td>GMT+14:00</td></tr>
     * <tr><td>Etc/GMT-2</td><td>GMT+02:00</td></tr>
     * <tr><td>Etc/GMT-3</td><td>GMT+03:00</td></tr>
     * <tr><td>Etc/GMT-4</td><td>GMT+04:00</td></tr>
     * <tr><td>Etc/GMT-5</td><td>GMT+05:00</td></tr>
     * <tr><td>Etc/GMT-6</td><td>GMT+06:00</td></tr>
     * <tr><td>Etc/GMT-7</td><td>GMT+07:00</td></tr>
     * <tr><td>Etc/GMT-8</td><td>GMT+08:00</td></tr>
     * <tr><td>Etc/GMT-9</td><td>GMT+09:00</td></tr>
     * <tr><td>Etc/GMT0</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Etc/Greenwich</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Etc/UCT</td><td>Coordinated Universal Time</td></tr>
     * <tr><td>Etc/UTC</td><td>Coordinated Universal Time</td></tr>
     * <tr><td>Etc/Universal</td><td>Coordinated Universal Time</td></tr>
     * <tr><td>Etc/Zulu</td><td>Coordinated Universal Time</td></tr>
     * <tr><td>Europe/Amsterdam</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Andorra</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Astrakhan</td><td>Astrakhan Time</td></tr>
     * <tr><td>Europe/Athens</td><td>Eastern European Time</td></tr>
     * <tr><td>Europe/Belfast</td><td>British Time</td></tr>
     * <tr><td>Europe/Belgrade</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Berlin</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Bratislava</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Brussels</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Bucharest</td><td>Eastern European Time</td></tr>
     * <tr><td>Europe/Budapest</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Busingen</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Chisinau</td><td>Eastern European Time</td></tr>
     * <tr><td>Europe/Copenhagen</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Dublin</td><td>Irish Time</td></tr>
     * <tr><td>Europe/Gibraltar</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Guernsey</td><td>British Time</td></tr>
     * <tr><td>Europe/Helsinki</td><td>Eastern European Time</td></tr>
     * <tr><td>Europe/Isle_of_Man</td><td>British Time</td></tr>
     * <tr><td>Europe/Istanbul</td><td>Turkey Time</td></tr>
     * <tr><td>Europe/Jersey</td><td>British Time</td></tr>
     * <tr><td>Europe/Kaliningrad</td><td>Eastern European Time</td></tr>
     * <tr><td>Europe/Kiev</td><td>Eastern European Time</td></tr>
     * <tr><td>Europe/Kirov</td><td>Moscow Time</td></tr>
     * <tr><td>Europe/Kyiv</td><td>Eastern European Time</td></tr>
     * <tr><td>Europe/Lisbon</td><td>Western European Time</td></tr>
     * <tr><td>Europe/Ljubljana</td><td>Central European Time</td></tr>
     * <tr><td>Europe/London</td><td>British Time</td></tr>
     * <tr><td>Europe/Luxembourg</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Madrid</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Malta</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Mariehamn</td><td>Eastern European Time</td></tr>
     * <tr><td>Europe/Minsk</td><td>Moscow Time</td></tr>
     * <tr><td>Europe/Monaco</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Moscow</td><td>Moscow Time</td></tr>
     * <tr><td>Europe/Nicosia</td><td>Eastern European Time</td></tr>
     * <tr><td>Europe/Oslo</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Paris</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Podgorica</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Prague</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Riga</td><td>Eastern European Time</td></tr>
     * <tr><td>Europe/Rome</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Samara</td><td>Samara Time</td></tr>
     * <tr><td>Europe/San_Marino</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Sarajevo</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Saratov</td><td>Saratov Time</td></tr>
     * <tr><td>Europe/Simferopol</td><td>Moscow Time</td></tr>
     * <tr><td>Europe/Skopje</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Sofia</td><td>Eastern European Time</td></tr>
     * <tr><td>Europe/Stockholm</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Tallinn</td><td>Eastern European Time</td></tr>
     * <tr><td>Europe/Tirane</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Tiraspol</td><td>Eastern European Time</td></tr>
     * <tr><td>Europe/Ulyanovsk</td><td>Ulyanovsk Time</td></tr>
     * <tr><td>Europe/Uzhgorod</td><td>Eastern European Time</td></tr>
     * <tr><td>Europe/Vaduz</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Vatican</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Vienna</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Vilnius</td><td>Eastern European Time</td></tr>
     * <tr><td>Europe/Volgograd</td><td>Volgograd Time</td></tr>
     * <tr><td>Europe/Warsaw</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Zagreb</td><td>Central European Time</td></tr>
     * <tr><td>Europe/Zaporozhye</td><td>Eastern European Time</td></tr>
     * <tr><td>Europe/Zurich</td><td>Central European Time</td></tr>
     * <tr><td>GB</td><td>British Time</td></tr>
     * <tr><td>GB-Eire</td><td>British Time</td></tr>
     * <tr><td>GMT</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>GMT0</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Greenwich</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Hongkong</td><td>Hong Kong Time</td></tr>
     * <tr><td>Iceland</td><td>Greenwich Mean Time</td></tr>
     * <tr><td>Indian/Antananarivo</td><td>Eastern Africa Time</td></tr>
     * <tr><td>Indian/Chagos</td><td>Indian Ocean Territory Time</td></tr>
     * <tr><td>Indian/Christmas</td><td>Christmas Island Time</td></tr>
     * <tr><td>Indian/Cocos</td><td>Cocos Islands Time</td></tr>
     * <tr><td>Indian/Comoro</td><td>Eastern Africa Time</td></tr>
     * <tr><td>Indian/Kerguelen</td><td>French Southern &amp; Antarctic Lands Time</td></tr>
     * <tr><td>Indian/Mahe</td><td>Seychelles Time</td></tr>
     * <tr><td>Indian/Maldives</td><td>Maldives Time</td></tr>
     * <tr><td>Indian/Mauritius</td><td>Mauritius Time</td></tr>
     * <tr><td>Indian/Mayotte</td><td>Eastern Africa Time</td></tr>
     * <tr><td>Indian/Reunion</td><td>Reunion Time</td></tr>
     * <tr><td>Iran</td><td>Iran Time</td></tr>
     * <tr><td>Israel</td><td>Israel Time</td></tr>
     * <tr><td>Jamaica</td><td>Eastern Time</td></tr>
     * <tr><td>Japan</td><td>Japan Time</td></tr>
     * <tr><td>Kwajalein</td><td>Marshall Islands Time</td></tr>
     * <tr><td>Libya</td><td>Eastern European Time</td></tr>
     * <tr><td>MET</td><td>Middle Europe Time</td></tr>
     * <tr><td>MST7MDT</td><td>Mountain Time</td></tr>
     * <tr><td>Mexico/BajaNorte</td><td>Pacific Time</td></tr>
     * <tr><td>Mexico/BajaSur</td><td>Mexican Pacific Time</td></tr>
     * <tr><td>Mexico/General</td><td>Central Time</td></tr>
     * <tr><td>NZ</td><td>New Zealand Time</td></tr>
     * <tr><td>NZ-CHAT</td><td>Chatham Time</td></tr>
     * <tr><td>Navajo</td><td>Mountain Time</td></tr>
     * <tr><td>PRC</td><td>China Time</td></tr>
     * <tr><td>PST8PDT</td><td>Pacific Time</td></tr>
     * <tr><td>Pacific/Apia</td><td>Apia Time</td></tr>
     * <tr><td>Pacific/Auckland</td><td>New Zealand Time</td></tr>
     * <tr><td>Pacific/Bougainville</td><td>Bougainville Time</td></tr>
     * <tr><td>Pacific/Chatham</td><td>Chatham Time</td></tr>
     * <tr><td>Pacific/Chuuk</td><td>Chuuk Time</td></tr>
     * <tr><td>Pacific/Easter</td><td>Easter Island Time</td></tr>
     * <tr><td>Pacific/Efate</td><td>Vanuatu Time</td></tr>
     * <tr><td>Pacific/Enderbury</td><td>Phoenix Is. Time</td></tr>
     * <tr><td>Pacific/Fakaofo</td><td>Tokelau Time</td></tr>
     * <tr><td>Pacific/Fiji</td><td>Fiji Time</td></tr>
     * <tr><td>Pacific/Funafuti</td><td>Tuvalu Time</td></tr>
     * <tr><td>Pacific/Galapagos</td><td>Galapagos Time</td></tr>
     * <tr><td>Pacific/Gambier</td><td>Gambier Time</td></tr>
     * <tr><td>Pacific/Guadalcanal</td><td>Solomon Is. Time</td></tr>
     * <tr><td>Pacific/Guam</td><td>Chamorro Time</td></tr>
     * <tr><td>Pacific/Honolulu</td><td>Hawaii-Aleutian Time</td></tr>
     * <tr><td>Pacific/Johnston</td><td>Hawaii-Aleutian Time</td></tr>
     * <tr><td>Pacific/Kanton</td><td>Kanton Time</td></tr>
     * <tr><td>Pacific/Kiritimati</td><td>Line Is. Time</td></tr>
     * <tr><td>Pacific/Kosrae</td><td>Kosrae Time</td></tr>
     * <tr><td>Pacific/Kwajalein</td><td>Marshall Islands Time</td></tr>
     * <tr><td>Pacific/Majuro</td><td>Marshall Islands Time</td></tr>
     * <tr><td>Pacific/Marquesas</td><td>Marquesas Time</td></tr>
     * <tr><td>Pacific/Midway</td><td>Samoa Time</td></tr>
     * <tr><td>Pacific/Nauru</td><td>Nauru Time</td></tr>
     * <tr><td>Pacific/Niue</td><td>Niue Time</td></tr>
     * <tr><td>Pacific/Norfolk</td><td>Norfolk Island Time</td></tr>
     * <tr><td>Pacific/Noumea</td><td>New Caledonia Time</td></tr>
     * <tr><td>Pacific/Pago_Pago</td><td>Samoa Time</td></tr>
     * <tr><td>Pacific/Palau</td><td>Palau Time</td></tr>
     * <tr><td>Pacific/Pitcairn</td><td>Pitcairn Time</td></tr>
     * <tr><td>Pacific/Pohnpei</td><td>Ponape Time</td></tr>
     * <tr><td>Pacific/Ponape</td><td>Ponape Time</td></tr>
     * <tr><td>Pacific/Port_Moresby</td><td>Papua New Guinea Time</td></tr>
     * <tr><td>Pacific/Rarotonga</td><td>Cook Islands Time</td></tr>
     * <tr><td>Pacific/Saipan</td><td>Chamorro Time</td></tr>
     * <tr><td>Pacific/Samoa</td><td>Samoa Time</td></tr>
     * <tr><td>Pacific/Tahiti</td><td>Tahiti Time</td></tr>
     * <tr><td>Pacific/Tarawa</td><td>Gilbert Is. Time</td></tr>
     * <tr><td>Pacific/Tongatapu</td><td>Tonga Time</td></tr>
     * <tr><td>Pacific/Truk</td><td>Chuuk Time</td></tr>
     * <tr><td>Pacific/Wake</td><td>Wake Time</td></tr>
     * <tr><td>Pacific/Wallis</td><td>Wallis &amp; Futuna Time</td></tr>
     * <tr><td>Pacific/Yap</td><td>Chuuk Time</td></tr>
     * <tr><td>Poland</td><td>Central European Time</td></tr>
     * <tr><td>Portugal</td><td>Western European Time</td></tr>
     * <tr><td>ROK</td><td>Korean Time</td></tr>
     * <tr><td>Singapore</td><td>Singapore Time</td></tr>
     * <tr><td>SystemV/AST4</td><td>Atlantic Time</td></tr>
     * <tr><td>SystemV/AST4ADT</td><td>Atlantic Time</td></tr>
     * <tr><td>SystemV/CST6</td><td>Central Time</td></tr>
     * <tr><td>SystemV/CST6CDT</td><td>Central Time</td></tr>
     * <tr><td>SystemV/EST5</td><td>Eastern Time</td></tr>
     * <tr><td>SystemV/EST5EDT</td><td>Eastern Time</td></tr>
     * <tr><td>SystemV/HST10</td><td>Hawaii-Aleutian Time</td></tr>
     * <tr><td>SystemV/MST7</td><td>Mountain Time</td></tr>
     * <tr><td>SystemV/MST7MDT</td><td>Mountain Time</td></tr>
     * <tr><td>SystemV/PST8</td><td>Pitcairn Time</td></tr>
     * <tr><td>SystemV/PST8PDT</td><td>Pacific Time</td></tr>
     * <tr><td>SystemV/YST9</td><td>Gambier Time</td></tr>
     * <tr><td>SystemV/YST9YDT</td><td>Alaska Time</td></tr>
     * <tr><td>Turkey</td><td>Turkey Time</td></tr>
     * <tr><td>UCT</td><td>Coordinated Universal Time</td></tr>
     * <tr><td>US/Alaska</td><td>Alaska Time</td></tr>
     * <tr><td>US/Aleutian</td><td>Hawaii-Aleutian Time</td></tr>
     * <tr><td>US/Arizona</td><td>Mountain Time</td></tr>
     * <tr><td>US/Central</td><td>Central Time</td></tr>
     * <tr><td>US/East-Indiana</td><td>Eastern Time</td></tr>
     * <tr><td>US/Eastern</td><td>Eastern Time</td></tr>
     * <tr><td>US/Hawaii</td><td>Hawaii-Aleutian Time</td></tr>
     * <tr><td>US/Indiana-Starke</td><td>Central Time</td></tr>
     * <tr><td>US/Michigan</td><td>Eastern Time</td></tr>
     * <tr><td>US/Mountain</td><td>Mountain Time</td></tr>
     * <tr><td>US/Pacific</td><td>Pacific Time</td></tr>
     * <tr><td>US/Samoa</td><td>Samoa Time</td></tr>
     * <tr><td>UTC</td><td>Coordinated Universal Time</td></tr>
     * <tr><td>Universal</td><td>Coordinated Universal Time</td></tr>
     * <tr><td>W-SU</td><td>Moscow Time</td></tr>
     * <tr><td>WET</td><td>Western European Time</td></tr>
     * <tr><td>Zulu</td><td>Coordinated Universal Time</td></tr>
     * </tbody></table>
     *
     * @return datetime the time zone was changed
     * @throws FormulaExecutionException if any argument is invalid
     * @since 1.0.0
     * @see DateTimeFormatter
     */
    @Override
    public String calculate(Argument... args) {

        final LocalDateTime srcDateTime = Argument.Utils.requireLocalDateTime(args[0]);
        final ZoneId srcZoneId = Argument.Utils.requireZoneId(args[1]);
        final ZoneId resultZoneId = Argument.Utils.requireZoneId(args[2]);

        return ZonedDateTime.of(srcDateTime, srcZoneId).withZoneSameInstant(resultZoneId)
                .format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

    }

    /**
     * {@inheritDoc}
     *
     * @since 1.0.0
     */
    @Override
    public ArgumentScheme getArgumentScheme() {
        return new ArgumentSchemeImpl(
                new ArgdefImpl("DateTime", "Source datetime. It format is \"uuuu-MM-ddTHH:mm:ss\"."),
                new ArgdefImpl("SourceZoneId", "ZoneId of source datetime."),
                new ArgdefImpl("ResultZoneId", "ZoneId of result datetime.")
        );
    }
}
