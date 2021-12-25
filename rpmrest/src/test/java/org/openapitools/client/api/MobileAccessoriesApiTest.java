package org.openapitools.client.api;

import org.openapitools.client.ApiClient;
import org.openapitools.client.model.AddChallengeModel;
import org.openapitools.client.model.AddEventModel;
import org.openapitools.client.model.AddFavouriteSongsModel;
import org.openapitools.client.model.AddPlaylistModel;
import org.openapitools.client.model.AddQueryDetailsModel;
import org.openapitools.client.model.AddUserHeightModel;
import org.openapitools.client.model.ChallengeStatusModel;
import org.openapitools.client.model.ChangePasswordModel;
import org.openapitools.client.model.CreateRequestModel;
import java.util.Date;
import org.openapitools.client.model.DistanceModel;
import org.openapitools.client.model.GetAllSongs;
import org.openapitools.client.model.GetEventdetailsModel;
import org.openapitools.client.model.GetGrearBrandModelListApiResponse;
import org.openapitools.client.model.GetGrearDetailsModel;
import org.openapitools.client.model.GetMyChallengeModel;
import org.openapitools.client.model.GetNotificationRequestsListApiResponse;
import org.openapitools.client.model.GetPlaylistModel;
import org.openapitools.client.model.GetUserGearModel;
import org.openapitools.client.model.GetVideoDetailsModel;
import org.openapitools.client.model.GroupAdminUserAdmin;
import org.openapitools.client.model.HeightModel;
import org.openapitools.client.model.ListUserModel;
import org.openapitools.client.model.ModelApiResponse;
import org.openapitools.client.model.ParticipantsdetailsModel;
import org.openapitools.client.model.ProfilePhotoModel;
import org.openapitools.client.model.QueryMasterModel;
import org.openapitools.client.model.StatusModel;
import org.openapitools.client.model.StringApiResponse;
import org.openapitools.client.model.StringServiceResponse;
import org.openapitools.client.model.UpdateEventModel;
import org.openapitools.client.model.UpdateSongsModel;
import org.openapitools.client.model.UserGearModel;
import org.openapitools.client.model.UserRequestModel;
import org.openapitools.client.model.UserResponseModel;
import org.openapitools.client.model.UsergearModel;
import org.openapitools.client.model.WeightModel;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for MobileAccessoriesApi
 */
public class MobileAccessoriesApiTest {

    private MobileAccessoriesApi api;

    @Before
    public void setup() {
        api = new ApiClient().createService(MobileAccessoriesApi.class);
    }

    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesAddFavouriteSongsPostTest() {
        List<AddFavouriteSongsModel> addFavouriteSongsModel = null;
        // ModelApiResponse response = api.apiMobileAccessoriesAddFavouriteSongsPost(addFavouriteSongsModel);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesAddGearDetailsPostTest() {
        UserGearModel userGearModel = null;
        // ModelApiResponse response = api.apiMobileAccessoriesAddGearDetailsPost(userGearModel);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesAddHeightandWeightPostTest() {
        AddUserHeightModel addUserHeightModel = null;
        // ModelApiResponse response = api.apiMobileAccessoriesAddHeightandWeightPost(addUserHeightModel);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesAddListInviteFriendsPostTest() {
        List<ParticipantsdetailsModel> participantsdetailsModel = null;
        // ModelApiResponse response = api.apiMobileAccessoriesAddListInviteFriendsPost(participantsdetailsModel);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesAddPlaylistPostTest() {
        AddPlaylistModel addPlaylistModel = null;
        // ModelApiResponse response = api.apiMobileAccessoriesAddPlaylistPost(addPlaylistModel);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesAddProfilePhotoPostTest() {
        ProfilePhotoModel profilePhotoModel = null;
        // ModelApiResponse response = api.apiMobileAccessoriesAddProfilePhotoPost(profilePhotoModel);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesAddUserQueryDetailsPostTest() {
        AddQueryDetailsModel addQueryDetailsModel = null;
        // ModelApiResponse response = api.apiMobileAccessoriesAddUserQueryDetailsPost(addQueryDetailsModel);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesCreateChallengePostTest() {
        AddChallengeModel addChallengeModel = null;
        // ModelApiResponse response = api.apiMobileAccessoriesCreateChallengePost(addChallengeModel);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesCreateConnectionRequestPostTest() {
        CreateRequestModel createRequestModel = null;
        // StringApiResponse response = api.apiMobileAccessoriesCreateConnectionRequestPost(createRequestModel);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesCreateEventPostTest() {
        AddEventModel addEventModel = null;
        // ModelApiResponse response = api.apiMobileAccessoriesCreateEventPost(addEventModel);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesDeleteEventDeleteTest() {
        Date eventDate = null;
        Long eventId = null;
        // ModelApiResponse response = api.apiMobileAccessoriesDeleteEventDelete(eventDate, eventId);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesDeletePlaylistSongsDeleteTest() {
        Long playListId = null;
        // ModelApiResponse response = api.apiMobileAccessoriesDeletePlaylistSongsDelete(playListId);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesEditChallengePostTest() {
        AddChallengeModel addChallengeModel = null;
        // ModelApiResponse response = api.apiMobileAccessoriesEditChallengePost(addChallengeModel);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesEditEventPostTest() {
        UpdateEventModel updateEventModel = null;
        // ModelApiResponse response = api.apiMobileAccessoriesEditEventPost(updateEventModel);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesEditMyPlaylistNamePostTest() {
        GetPlaylistModel getPlaylistModel = null;
        // ModelApiResponse response = api.apiMobileAccessoriesEditMyPlaylistNamePost(getPlaylistModel);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetAllChallengeStatusGetTest() {
        // List<ChallengeStatusModel> response = api.apiMobileAccessoriesGetAllChallengeStatusGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetAllGearBrandGetTest() {
        // GetGrearBrandModelListApiResponse response = api.apiMobileAccessoriesGetAllGearBrandGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetAllMeasureDistanceGetTest() {
        // List<DistanceModel> response = api.apiMobileAccessoriesGetAllMeasureDistanceGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetAllMeasureHeightGetTest() {
        // List<HeightModel> response = api.apiMobileAccessoriesGetAllMeasureHeightGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetAllMeasureWeightGetTest() {
        // List<WeightModel> response = api.apiMobileAccessoriesGetAllMeasureWeightGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetAllVideoDetailsByCategoryIdGetTest() {
        Integer categoryId = null;
        // List<GetVideoDetailsModel> response = api.apiMobileAccessoriesGetAllVideoDetailsByCategoryIdGet(categoryId);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetAllVideoDetailsGetTest() {
        // List<GetVideoDetailsModel> response = api.apiMobileAccessoriesGetAllVideoDetailsGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetEventDetailsGetTest() {
        Date eventDate = null;
        // List<GetEventdetailsModel> response = api.apiMobileAccessoriesGetEventDetailsGet(eventDate);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetGearDetailsByBrandIdIdGetTest() {
        String id = null;
        Long brandId = null;
        // List<GetGrearDetailsModel> response = api.apiMobileAccessoriesGetGearDetailsByBrandIdIdGet(id, brandId);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetGroupUserDetailsByInviteCodeGetTest() {
        String inviteCode = null;
        // List<GroupAdminUserAdmin> response = api.apiMobileAccessoriesGetGroupUserDetailsByInviteCodeGet(inviteCode);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetListUserListGetTest() {
        // List<ListUserModel> response = api.apiMobileAccessoriesGetListUserListGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetMyCurrentGearByIdGetTest() {
        UsergearModel usergearModel = null;
        // List<GetUserGearModel> response = api.apiMobileAccessoriesGetMyCurrentGearByIdGet(usergearModel);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetMyCurrentGearlistGetTest() {
        // List<GetUserGearModel> response = api.apiMobileAccessoriesGetMyCurrentGearlistGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetMyPlaylistGetTest() {
        // List<GetPlaylistModel> response = api.apiMobileAccessoriesGetMyPlaylistGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetMylistOfChallengeGetTest() {
        // List<GetMyChallengeModel> response = api.apiMobileAccessoriesGetMylistOfChallengeGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetMylistOfCompletedChallengeGetTest() {
        // List<GetMyChallengeModel> response = api.apiMobileAccessoriesGetMylistOfCompletedChallengeGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetNotificationListForConnectionsGetTest() {
        // GetNotificationRequestsListApiResponse response = api.apiMobileAccessoriesGetNotificationListForConnectionsGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetPlaylistSongsGetTest() {
        Long playlistId = null;
        // List<GetAllSongs> response = api.apiMobileAccessoriesGetPlaylistSongsGet(playlistId);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetQueryMasterGetTest() {
        // List<QueryMasterModel> response = api.apiMobileAccessoriesGetQueryMasterGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetUserProfileGetTest() {
        // UserResponseModel response = api.apiMobileAccessoriesGetUserProfileGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetlistSongsDetailsGetTest() {
        // List<UpdateSongsModel> response = api.apiMobileAccessoriesGetlistSongsDetailsGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesGetlistfavoriteSongsGetTest() {
        // List<GetAllSongs> response = api.apiMobileAccessoriesGetlistfavoriteSongsGet();

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesLeaveChallengePostTest() {
        StatusModel statusModel = null;
        // ModelApiResponse response = api.apiMobileAccessoriesLeaveChallengePost(statusModel);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesSetPlaylistSongFavouritePostTest() {
        Long playlistSongId = null;
        // StringServiceResponse response = api.apiMobileAccessoriesSetPlaylistSongFavouritePost(playlistSongId);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesSetUserPasswordPatchTest() {
        String password = null;
        String inviteCode = null;
        // ModelApiResponse response = api.apiMobileAccessoriesSetUserPasswordPatch(password, inviteCode);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesUpdateRetireGearPostTest() {
        UsergearModel usergearModel = null;
        // ModelApiResponse response = api.apiMobileAccessoriesUpdateRetireGearPost(usergearModel);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesUpdateUserProfilePostTest() {
        UserRequestModel userRequestModel = null;
        // ModelApiResponse response = api.apiMobileAccessoriesUpdateUserProfilePost(userRequestModel);

        // TODO: test validations
    }
    /**
     * 
     *
     * 
     */
    @Test
    public void apiMobileAccessoriesUserChangePasswordPatchTest() {
        ChangePasswordModel changePasswordModel = null;
        // ModelApiResponse response = api.apiMobileAccessoriesUserChangePasswordPatch(changePasswordModel);

        // TODO: test validations
    }
}
