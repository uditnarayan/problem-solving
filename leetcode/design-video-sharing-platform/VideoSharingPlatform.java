// https://leetcode.com/problems/design-video-sharing-platform/

import java.util.*;

class Video {
    private int id;
    private String content;
    private int views;
    private int likes;
    private int dislikes;

    public Video(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public String watch(int startMinute, int endMinute) {
        int end = Math.min(endMinute, content.length()-1);
        this.views++;
        return content.substring(startMinute, end + 1);
    }

    public void like() {
        this.likes++;
    }
    
    public void dislike() {
        this.dislikes++;
    }

    public int[] getLikesAndDislikes() {
        return new int[]{this.likes, this.dislikes};
    }
    
    public int getViews() {
        return this.views;
    }

}

class VideoSharingPlatform {
    private int nextVideoId;

    private Queue<Integer> availableVideoIds;

    private Map<Integer, Video> videos;

    public VideoSharingPlatform() {
        this.nextVideoId = 0;
        this.availableVideoIds = new PriorityQueue<>();
        this.videos = new HashMap<>();
    }
    
    public int upload(String video) {
        int id = this.getNextVideoId();
        videos.put(id, new Video(id, video));
        return id;
    }
    
    public void remove(int videoId) {
        Video video = videos.get(videoId);
        if(video != null) {
            this.availableVideoIds.offer(videoId);
            videos.remove(videoId);
        }
    }
    
    public String watch(int videoId, int startMinute, int endMinute) {
        Video video = videos.get(videoId);
        if (video != null) {
            return video.watch(startMinute, endMinute);
        } 
        return "-1";
    }
    
    public void like(int videoId) {
        Video video = videos.get(videoId);
        if (video != null) {
            video.like();
        } 
    }
    
    public void dislike(int videoId) {
        Video video = videos.get(videoId);
        if (video != null) {
            video.dislike();
        }
    }
    
    public int[] getLikesAndDislikes(int videoId) {
        Video video = videos.get(videoId);
        if (video != null) {
            return video.getLikesAndDislikes();
        } 
        return new int[]{ -1 };
    }
    
    public int getViews(int videoId) {
        Video video = videos.get(videoId);
        if (video != null) {
            return video.getViews();
        } 
        return -1;
    }

    private int getNextVideoId() {
        if (!this.availableVideoIds.isEmpty()) {
            return this.availableVideoIds.poll();
        }
        int next = this.nextVideoId;
        this.nextVideoId++;
        return next;
    }
}
