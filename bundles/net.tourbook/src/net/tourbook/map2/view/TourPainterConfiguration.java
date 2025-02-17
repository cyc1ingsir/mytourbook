/*******************************************************************************
 * Copyright (C) 2005, 2022 Wolfgang Schramm and Contributors
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation version 2 of the License.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110, USA
 *******************************************************************************/
package net.tourbook.map2.view;

import java.util.ArrayList;
import java.util.Set;

import net.tourbook.common.color.IMapColorProvider;
import net.tourbook.common.map.GeoPosition;
import net.tourbook.data.TourData;
import net.tourbook.photo.Photo;
import net.tourbook.tour.filter.TourFilterFieldOperator;

/**
 * Contains data which are needed to paint a tour into the 2D map.
 */
public class TourPainterConfiguration {

   private static TourPainterConfiguration _instance;

   private final ArrayList<TourData>       _allTourData = new ArrayList<>();
   private final ArrayList<Photo>          _allPhotos   = new ArrayList<>();

   /**
    * contains the upper left and lower right position for a tour
    */
   private Set<GeoPosition>                _tourBounds;

   private int                             _zoomLevelAdjustment;

   private IMapColorProvider               _mapColorProvider;

   boolean                                 isBackgroundDark;

   boolean                                 isShowStartEndInMap;
   boolean                                 isShowTourMarker;
   boolean                                 isShowTourPauses;
   boolean                                 isShowWayPoints;
   boolean                                 isPhotoVisible;
   boolean                                 isTourVisible;

   /**
    * Is <code>true</code> when a link photo is displayed, otherwise a tour photo (photo which is
    * save in a tour) is displayed.
    */
   boolean                                 isLinkPhotoDisplayed;

   boolean                                 isFilterTourPauses;
   boolean                                 isFilterPauseDuration;
   boolean                                 isShowAutoPauses;
   boolean                                 isShowUserPauses;
   long                                    pauseDuration;
   Enum<TourFilterFieldOperator>           pauseDurationOperator;

   boolean                                 isShowBreadcrumbs;

   private TourPainterConfiguration() {}

   public static TourPainterConfiguration getInstance() {

      if (_instance == null) {
         _instance = new TourPainterConfiguration();
      }

      return _instance;
   }

   public IMapColorProvider getMapColorProvider() {
      return _mapColorProvider;
   }

   public ArrayList<Photo> getPhotos() {
      return _allPhotos;
   }

   /**
    * @return Returns the tour bounds or <code>null</code> when a tour is not set
    */
   public Set<GeoPosition> getTourBounds() {
      return _tourBounds;
   }

   /**
    * @return Returns the current {@link TourData} which is selected in a view or editor
    */
   public ArrayList<TourData> getTourData() {
      return _allTourData;
   }

   public int getZoomLevelAdjustment() {
      return _zoomLevelAdjustment;
   }

   /**
    * Do not draw a tour
    *
    * @param tourData
    */
   public void resetTourData() {

      _allTourData.clear();
      _allTourData.add(null);
   }

   public void setMapColorProvider(final IMapColorProvider mapColorProvider) {
      if (mapColorProvider != null) {
         _mapColorProvider = mapColorProvider;
      }
   }

   /**
    * @param allPhotos
    *           When <code>null</code>, photos are not displayed.
    * @param isShowPhoto
    */
   public void setPhotos(final ArrayList<Photo> allPhotos, final boolean isShowPhoto, final boolean isLinkPhoto) {

      _allPhotos.clear();

      if (allPhotos != null) {
         _allPhotos.addAll(allPhotos);
      }

      isPhotoVisible = isShowPhoto && _allPhotos.size() > 0;

      isLinkPhotoDisplayed = isLinkPhoto;
   }

   public void setTourBounds(final Set<GeoPosition> mapPositions) {
      _tourBounds = mapPositions;
   }

   /**
    * Sets {@link TourData} for all tours which are displayed
    *
    * @param tourDataList
    * @param isShowTour
    */
   public void setTourData(final ArrayList<TourData> tourDataList, final boolean isShowTour) {

      _allTourData.clear();

      if (tourDataList != null) {
         _allTourData.addAll(tourDataList);
      }

      isTourVisible = isShowTour && _allTourData.size() > 0;
   }

   /**
    * Set {@link TourData} which is used for the next painting or <code>null</code> to not draw the
    * tour
    *
    * @param tourData
    * @param isShowTour
    */
   public void setTourData(final TourData tourData, final boolean isShowTour) {

      _allTourData.clear();
      _allTourData.add(tourData);

      isTourVisible = isShowTour && _allTourData.size() > 0;
   }

   public void setZoomLevelAdjustment(final int zoomLevel) {
      _zoomLevelAdjustment = zoomLevel;
   }
}
