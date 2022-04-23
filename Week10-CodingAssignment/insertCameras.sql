use cameras;
insert into cameras (mfr_id, cam_name, cam_type, image_type, year_of_release)
Values
((SELECT mfr_id FROM manufacturers WHERE mfr_name = 'Lieca'), 'Leica IIIf', '35mm Rangefinder', 'film', 1933),
((SELECT mfr_id FROM manufacturers WHERE mfr_name = 'Lieca'), 'Leica 250', '35mm Rangefinder', 'film', 1933),
((SELECT mfr_id FROM manufacturers WHERE mfr_name = 'Lieca'), 'Leica IIIg', '35mm Rangefinder', 'film', 1957),
((SELECT mfr_id FROM manufacturers WHERE mfr_name = 'Fujifilm'), 'X100', 'Rangefinder', 'digital', 2011),
((SELECT mfr_id FROM manufacturers WHERE mfr_name = 'Hasselblad'), 'HK7', 'Modular', 'film', 1941);

